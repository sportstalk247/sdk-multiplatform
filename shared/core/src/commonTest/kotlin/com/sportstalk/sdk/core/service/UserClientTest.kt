package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.core.RandomString
import com.sportstalk.sdk.core.ServiceFactory
import com.sportstalk.sdk.core.SportsTalk247
import com.sportstalk.sdk.core.api.ChatClient
import com.sportstalk.sdk.core.api.UserClient
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.Kind
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.chat.*
import com.sportstalk.sdk.model.reports.ReportType
import com.sportstalk.sdk.model.user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import kotlin.random.Random
import kotlin.test.*

class UserClientTest {

    private lateinit var config: ClientConfig
    private lateinit var userService: UserClient
    private lateinit var chatService: ChatClient
    private lateinit var json: Json

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(scheduler = testScope.testScheduler)


    @BeforeTest
    fun setup() {
        config = ClientConfig(
            appId = "63c16f13c3e89411881ba085",
            apiToken = "cXSVhVOVYEewANzl7CuoWgw08gtq8FTUS4nxI_pHcQKg",
            endpoint = "https://api.sportstalk247.com/api/v3"
        )
        json = ServiceFactory.RestApi.json
        userService = SportsTalk247.UserClient(config)
        chatService = SportsTalk247.ChatClient(config)

        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun cleanUp() {
        // Clean-up test dispatcher
        Dispatchers.resetMain()
    }

    /**
     * Helper function to clean up Test Users from the Backend Server
     */
    private suspend fun deleteTestUsers(vararg userIds: String?) {
        for (id in userIds) {
            id ?: continue
            try {
                userService.deleteUser(userId = id)
            } catch (_: Throwable) {}
        }
    }

    /**
     * Helper function to clean up Test Users from the Backend Server
     */
    private suspend fun deleteTestChatRooms(vararg chatRoomIds: String?) {
        for (id in chatRoomIds) {
            id ?: continue
            try {
                chatService.deleteRoom(chatRoomId = id)
            } catch (_: Throwable) {}
        }
    }

    @Test
    fun `A-ERROR-403 - Request is not authorized with a token`() = testScope.runTest {
        val userCaseUserService = ServiceFactory.User.get(
            config.copy(
                apiToken = "not-a-valid-auth-api-token"
            )
        )

        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                userCaseUserService.createOrUpdateUser(request = testInputRequest)
            }

            fail("Expecting to throw an exception")
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-403 - Request is not authorized with a token`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "Request is not authorized with a token." }
            assertTrue { err.code == 403 }
        }
    }

    @Test
    fun `A - Create or Update User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )
        val testExpectedResult = User(
            kind = Kind.USER,
            userid = testInputRequest.userid,
            handle = testInputRequest.handle,
            handlelowercase = testInputRequest.handle!!.lowercase(),
            displayname = testInputRequest.displayname
        )

        // WHEN
        try {
            val testActualResult = withContext(testDispatcher) {
                userService.createOrUpdateUser(request = testInputRequest)
            }

            // THEN
            println(
                "`Create or Update User`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }

    }

    @Test
    fun `A-ERROR-405 - Create or Update User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle~test1~$testUserId",
            displayname = "Test 1"
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.createOrUpdateUser(request = testInputRequest)
            }

            fail("Expecting to throw an exception")
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-405 - Create or Update User`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The handle requested (${testInputRequest.handle!!}) contains characters that are not allowed.  Use only [abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_]" }
            assertTrue { err.code == 405 }

        }
    }

    @Test
    fun `B - Delete User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )
        // Should create a test user first
        try {
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            val testExpectedResult = DeleteUserResponse(
                kind = Kind.DELETED_USER,
                user = testCreatedUser
            )

            // WHEN
            val testActualResult = userService.deleteUser(
                userId = testCreatedUser.userid!!
            )

            // THEN
            println(
                "`Delete User`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteUserResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.user?.userid == testExpectedResult.user?.userid }
            assertTrue { testActualResult.user?.handle == testExpectedResult.user?.handle!! }
            assertTrue { testActualResult.user?.handlelowercase == testExpectedResult.user?.handlelowercase!! }
            assertTrue { testActualResult.user?.displayname == testExpectedResult.user?.displayname }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }

    }

    @Test
    fun `B-ERROR-404 - Delete User`() = testScope.runTest {
        // GIVEN
        val testInputUserId = "non-existing-ID-1234"

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.deleteUser(
                    userId = testInputUserId
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Delete User`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specifed user $testInputUserId does not exist." }
            assertTrue { err.code == 404 }
        }

    }

    @Test
    fun `C - Get User Details`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        try {
            // Should create a test user first
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            val testExpectedResult = testCreatedUser.copy()

            // WHEN
            val testActualResult = userService.getUserDetails(
                userId = testCreatedUser.userid!!
            )

            // THEN
            println(
                "`Get User Details`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `C-ERROR-404 - Get User Details`() = testScope.runTest {
        // GIVEN
        val testInputUserId = "non-existing-ID-1234"

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.getUserDetails(
                    userId = testInputUserId
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Get User Details`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified UserID was not found." }
            assertTrue { err.code == 404 }

        }
    }

    @Test
    fun `D - List Users`() = testScope.runTest {
        // GIVEN
        val testUserId1 = RandomString.make(16)
        val testInputRequest1 = CreateUpdateUserRequest(
            userid = testUserId1,
            handle = "list_users_first",
            displayname = "Test List Users 1"
        )
        val testUserId2 = RandomString.make(16)
        val testInputRequest2 = CreateUpdateUserRequest(
            userid = testUserId2,
            handle = "list_users_second",
            displayname = "Test List Users 2"
        )

        try {
            // Should create a test user first
            val testCreatedUser1 = userService.createOrUpdateUser(request = testInputRequest1)
            val testCreatedUser2 = userService.createOrUpdateUser(request = testInputRequest2)

            val testExpectedResult = ListUsersResponse(
                kind = Kind.USER_LIST,
                users = listOf(testCreatedUser1, testCreatedUser2)
            )

            val testInputLimit = 10

            // WHEN
            val testActualResult1 = userService.listUsers(
                limit = testInputLimit,
                cursor = testCreatedUser1.userid
            )
            val testActualResult2 = userService.listUsers(
                limit = testInputLimit,
                cursor = testCreatedUser2.userid
            )

            // THEN
            println(
                "`List Users`() -> testActualResult1 = " +
                        json.encodeToString(
                            ListUsersResponse.serializer(),
                            testActualResult1
                        )
            )
            println(
                "`List Users`() -> testActualResult2 = " +
                        json.encodeToString(
                            ListUsersResponse.serializer(),
                            testActualResult2
                        )
            )

            assertTrue { testActualResult1.kind == testExpectedResult.kind }
            assertTrue { testActualResult1.users.isNotEmpty() }
            assertTrue { testActualResult1.users.any { it.userid == testCreatedUser1.userid } }

            assertTrue { testActualResult2.kind == testExpectedResult.kind }
            assertTrue { testActualResult2.users.isNotEmpty() }
            assertTrue { testActualResult2.users.any { it.userid == testCreatedUser2.userid } }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(
                testUserId1, testUserId2
            )
        }
    }

    @Test
    fun `E - Ban User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )
        // Should create a test user first
        try {
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            val testExpectedResult = testCreatedUser.copy()

            // WHEN
            val testActualResult = userService.setBanStatus(
                userId = testCreatedUser.userid!!,
                applyeffect = true,
                expireseconds = 3_000_000L
            )

            // THEN
            println(
                "`Ban User`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            // "@handle_test1 was banned"
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
            assertTrue { testActualResult.banned == true }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }

    }

    @Test
    fun `E-ERROR-404 - Ban User`() = testScope.runTest {
        // GIVEN
        val testInputUserId = "non-existing-ID-1234"

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.setBanStatus(
                    userId = testInputUserId,
                    applyeffect = true,
                    expireseconds = 3_000_000L
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Ban User`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified user ($testInputUserId) was not found." }
            assertTrue { err.code == 404 }
        }
    }

    @Test
    fun `F - Restore User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )
        // Should create a test user first
        try {
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            // The test user should be BANNED first
            userService.setBanStatus(
                userId = testCreatedUser.userid!!,
                applyeffect = true,
                expireseconds = 3_000_000L
            )

            val testExpectedResult = testCreatedUser.copy()

            // WHEN
            val testActualResult = userService.setBanStatus(
                userId = testCreatedUser.userid!!,
                applyeffect = false
            )

            // THEN
            println(
                "`Restore User`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            // "@handle_test1 was restored"
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
            assertTrue { testActualResult.banned == false }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `F-ERROR-404 - Restore User`() = testScope.runTest {
        // GIVEN
        val testInputUserId = "non-existing-ID-1234"

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.setBanStatus(
                    userId = testInputUserId,
                    applyeffect = false
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Restore User`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified user ($testInputUserId) was not found." }
            assertTrue { err.code == 404 }

        }
    }

    @Test
    fun `F1 - Search Users - By Handle`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest1 = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "list_users_first",
            displayname = "Test List Users 1"
        )

        try {
            // Should create a test user(s) first
            val testCreatedUser1 = userService.createOrUpdateUser(request = testInputRequest1)

            val testExpectedResult = ListUsersResponse(
                kind = Kind.USER_LIST,
                users = listOf(testCreatedUser1)
            )

            val testInputLimit = 10

            // WHEN
            val testActualResult1 = userService.searchUsers(
                handle = testCreatedUser1.handle!!,
                limit = testInputLimit
            )

            // THEN
            println(
                "`Search Users`() -> testActualResult1 = " +
                        json.encodeToString(
                            ListUsersResponse.serializer(),
                            testActualResult1
                        )
            )

            assertTrue { testActualResult1.kind == testExpectedResult.kind }
            assertTrue { testActualResult1.users.isNotEmpty() }
            assertTrue { testActualResult1.users.any { it.handle == testCreatedUser1.handle } }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `F2 - Search Users - By Name`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest1 = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "list_users_first",
            displayname = "Test List Users 1"
        )

        try {
            // Should create a test user(s) first
            val testCreatedUser1 = userService.createOrUpdateUser(request = testInputRequest1)

            val testExpectedResult = ListUsersResponse(
                kind = Kind.USER_LIST,
                users = listOf(testCreatedUser1)
            )

            val testInputLimit = 10

            // WHEN
            val testActualResult1 = userService.searchUsers(
                name = testCreatedUser1.displayname,
                limit = testInputLimit
            )

            // THEN
            println(
                "`Search Users`() -> testActualResult1 = " +
                        json.encodeToString(
                            ListUsersResponse.serializer(),
                            testActualResult1
                        )
            )

            assertTrue { testActualResult1.kind == testExpectedResult.kind }
            assertTrue { testActualResult1.users.isNotEmpty() }
            assertTrue { testActualResult1.users.any { it.displayname == testCreatedUser1.displayname } }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }

    }

    @Test
    fun `F3 - Search Users - By UserId`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest1 = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "list_users_first",
            displayname = "Test List Users 1"
        )

        try {
            // Should create a test user(s) first
            val testCreatedUser1 = userService.createOrUpdateUser(request = testInputRequest1)

            val testExpectedResult = ListUsersResponse(
                kind = Kind.USER_LIST,
                users = listOf(testCreatedUser1)
            )

            val testInputLimit = 10

            // WHEN
            val testActualResult1 = userService.searchUsers(
                userid = testCreatedUser1.userid!!,
                limit = testInputLimit
            )

            // THEN
            println(
                "`Search Users`() -> testActualResult1 = " +
                        json.encodeToString(
                            ListUsersResponse.serializer(),
                            testActualResult1
                        )
            )

            assertTrue { testActualResult1.kind == testExpectedResult.kind }
            assertTrue { testActualResult1.users.isNotEmpty() }
            assertTrue { testActualResult1.users.any { it.userid == testCreatedUser1.userid } }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `F-ERROR-400 - Search Users`() = testScope.runTest {
        // GIVEN

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.searchUsers(
                    // No search criteria provided
                    limit = 100
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-400 - Search Users`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "Search requires either a userid, handle or name parameter." }
            assertTrue { err.code == 400 }
        }
    }

    @Test
    fun `G - Set Shadow Ban Status`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        try {
            // Should create a test user first
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            val testExpectedResult = testCreatedUser.copy(
                shadowbanned = true
            )

            // WHEN
            val testActualResult = userService.setShadowBanStatus(
                userId = testCreatedUser.userid!!,
                applyeffect = true,
                expireseconds = 3_000_000L
            )

            // THEN
            println(
                "`Set Shadow Ban Status`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            // "@handle_test1 was banned"
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
            assertTrue { testActualResult.shadowbanned == true }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `H - Globally Purge User Content`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        // WHEN
        try {
            // Should create a test user first
            userService.createOrUpdateUser(request = testInputRequest)

            val testActualResult = userService.globallyPurgeUserContent(
                userId = testInputRequest.userid,
                byuserid = testInputRequest.userid
            )

            // THEN
            println(
                "`Globally Purge User Content`() -> testActualResult = \n" +
                        json.encodeToString(
                            GloballyPurgeUserContentResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { true }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }

    }

    @Test
    fun `I - Report User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputCreateRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        try {
            // Should create a test user first
            val testCreatedUser = userService.createOrUpdateUser(request = testInputCreateRequest)

            val testInputRequest = ReportUserRequest(
                userid = testCreatedUser.userid!!,
                reporttype = ReportType.ABUSE
            )
            val testExpectedResult = testCreatedUser.copy(
                reports = listOf(
                    UserReport(
                        userid = testInputRequest.userid,
                        reason = testInputRequest.reporttype
                    )
                )
            )

            // WHEN
            val testActualResult = userService.reportUser(
                userId = testInputRequest.userid!!,
                reporttype = testInputRequest.reporttype!!
            )

            // THEN
            println(
                "`Report User`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
            // A User Report will be added
            assertTrue {
                testActualResult.reports?.find { _rprt ->
                    _rprt.userid == testInputRequest.userid
                            && _rprt.reason == testInputRequest.reporttype
                } != null
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `J-1 - List User Notifications - Chat Reply`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(200L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            delay(200L)
            advanceUntilIdle()
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            delay(2000L)
            advanceUntilIdle()
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = userService.listUserNotifications(
                userId = testInputChatReplyThreadedRequest.userid,
                limit = 10,
                filterNotificationTypes = listOf(UserNotification.Type.CHAT_REPLY),
                cursor = null,
                includeread = false,
                filterChatRoomId = testCreatedChatRoomData.id
            )

            // THEN
            println(
                "`List User Notifications - Chat Reply`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListUserNotificationsResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.LIST_USER_NOTIFICATIONS }
            assertTrue {
                testActualResult.notifications.any { notif ->
                    notif.userid == testCreatedUserData.userid
                            && notif.chatroomid == testCreatedChatRoomData.id
                            && notif.chateventid == testChatReplyThreaded.id
                            && notif.notificationtype == UserNotification.Type.CHAT_REPLY
                }
            }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `J-2 - List User Notifications - Chat Quote`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            delay(300L)

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            delay(300L)

            val testInputChatQuoteRequest = SendQuotedReplyRequest(
                body = "This is Jessy, quote to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Quote
            val testChatReplyQuote = chatService.sendQuotedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatQuoteRequest
            )

            delay(300L)

            // WHEN
            val testActualResult = userService.listUserNotifications(
                userId = testInputChatQuoteRequest.userid,
                limit = 10,
                filterNotificationTypes = listOf(UserNotification.Type.CHAT_QUOTE),
                cursor = null,
                includeread = false,
                filterChatRoomId = testCreatedChatRoomData.id
            )

            // THEN
            println(
                "`List User Notifications - Chat Quote`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListUserNotificationsResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.LIST_USER_NOTIFICATIONS }
            assertTrue {
                testActualResult.notifications.any { notif ->
                    notif.userid == testCreatedUserData.userid
                            && notif.chatroomid == testCreatedChatRoomData.id
                            && notif.chateventid == testChatReplyQuote.id
                            && notif.notificationtype == UserNotification.Type.CHAT_QUOTE
                }
            }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `K - Set User Notification As Read`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            delay(200L)

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            delay(200L)

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            delay(200L)

            val listUserNotifications = userService.listUserNotifications(
                userId = testInputChatReplyThreadedRequest.userid,
                filterNotificationTypes = listOf(UserNotification.Type.CHAT_REPLY),
                limit = 10,
                includeread = false
            )

            val testUserNotification = listUserNotifications.notifications.firstOrNull() ?: fail()

            // WHEN
            val testActualResult = userService.setUserNotificationAsRead(
                userId = testUserNotification.userid!!,
                notificationId = testUserNotification.id!!,
                read = true
            )

            // THEN
            println(
                "`Set User Notification As Read`() -> testActualResult = \n" +
                        json.encodeToString(
                            UserNotification.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.NOTIFICATION }
            assertTrue { testActualResult.id == testUserNotification.id }
            assertTrue { testActualResult.userid == testUserNotification.userid }
            assertTrue { testActualResult.isread == true }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `L - Set User Notification As Read by Chat Event`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            delay(200L)

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            delay(200L)

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            delay(200L)

            // WHEN
            val testActualResult = userService.setUserNotificationAsReadByChatEvent(
                userId = testCreatedUserData.userid!!,
                chatEventId = testChatReplyThreaded.id!!,
                read = true
            )

            // THEN
            println(
                "`Set User Notification As Read by Chat Event`() -> testActualResult = \n" +
                        json.encodeToString(
                            UserNotification.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.NOTIFICATION }
            assertTrue { testActualResult.userid == testCreatedUserData.userid!! }
            assertTrue { testActualResult.chateventid == testChatReplyThreaded.id!! }
            assertTrue { testActualResult.isread == true }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `M - Delete User Notification`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            delay(200L)

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            delay(200L)

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            delay(200L)

            val listUserNotifications = userService.listUserNotifications(
                userId = testInputChatReplyThreadedRequest.userid,
                filterNotificationTypes = listOf(UserNotification.Type.CHAT_REPLY),
                limit = 10,
                includeread = false
            )

            val testUserNotification = listUserNotifications.notifications.firstOrNull() ?: fail()

            // WHEN
            val testActualResult = userService.deleteUserNotification(
                userId = testUserNotification.userid!!,
                notificationId = testUserNotification.id!!
            )

            // THEN
            println(
                "`Delete User Notification`() -> testActualResult = \n" +
                        json.encodeToString(
                            UserNotification.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.NOTIFICATION }
            assertTrue { testActualResult.id == testUserNotification.id }
            assertTrue { testActualResult.userid == testUserNotification.userid }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `N - Delete User Notification by Chat Event`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(200L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            delay(200L)
            advanceUntilIdle()
            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            delay(2000L)
            advanceUntilIdle()
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = userService.deleteUserNotificationByChatEvent(
                userId = testCreatedUserData.userid!!,
                chatEventId = testChatReplyThreaded.id!!
            )

            // THEN
            println(
                "`Delete User Notification by Chat Event`() -> testActualResult = \n" +
                        json.encodeToString(
                            UserNotification.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.NOTIFICATION }
            assertTrue { testActualResult.userid == testCreatedUserData.userid!! }
            assertTrue { testActualResult.chateventid == testChatReplyThreaded.id!! }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `O - Mark All User Notifications as Read`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        // Should create a test chat room first
        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData?.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatReplyThreadedRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )

            // Perform Chat Reply - Threaded
            val testChatReplyThreaded = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputChatReplyThreadedRequest
            )

            val listUserNotifications = userService.listUserNotifications(
                userId = testInputChatReplyThreadedRequest.userid,
                filterNotificationTypes = listOf(UserNotification.Type.CHAT_REPLY),
                limit = 10,
                includeread = false
            )

            val testUserNotification = listUserNotifications.notifications.firstOrNull() ?: fail()

            try {
                // WHEN
                val response = userService.markAllUserNotificationsAsRead(
                    userid = testCreatedUserData.userid!!,
                    delete = true
                )

                // THEN
                println(
                    "`Mark All User Notifications as Read`() -> testActualResult = \n" +
                            response
                )

                assertTrue { true }

            } catch (err: SportsTalkException) {
                err.printStackTrace()
                fail(err.message)
            }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `P - Mute User`() = testScope.runTest {
        // GIVEN
        val testUserId = RandomString.make(16)
        val testInputRequest = CreateUpdateUserRequest(
            userid = testUserId,
            handle = "handle_test1_$testUserId",
            displayname = "Test 1"
        )

        try {
            // Should create a test user first
            val testCreatedUser = userService.createOrUpdateUser(request = testInputRequest)

            val testExpectedResult = testCreatedUser.copy()

            // WHEN
            val testActualResult = userService.muteUser(
                userId = testCreatedUser.userid!!,
                applyeffect = true,
                expireseconds = 3_000_000L
            )

            // THEN
            println(
                "`Mute User`() -> testActualResult = \n" +
                        json.encodeToString(
                            User.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            // "@handle_test1 was banned"
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.handle == testExpectedResult.handle!! }
            assertTrue { testActualResult.handlelowercase == testExpectedResult.handlelowercase!! }
            assertTrue { testActualResult.displayname == testExpectedResult.displayname }
            assertTrue { testActualResult.muted == true }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test User
            deleteTestUsers(testUserId)
        }
    }

    @Test
    fun `P-ERROR-404 - Mute User`() = testScope.runTest {
        // GIVEN
        val testInputUserId = "non-existing-ID-1234"

        // WHEN
        try {
            withContext(testDispatcher) {
                userService.muteUser(
                    userId = testInputUserId,
                    applyeffect = true,
                    expireseconds = 3_000_000L
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Mute User`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified user ($testInputUserId) was not found." }
            assertTrue { err.code == 404 }
        }
    }

    object TestData {
        val ADMIN_PASSWORD = "zola"

        private val USER_HANDLE_RANDOM_NUM = Random(Clock.System.now().toEpochMilliseconds())
        private val CHATROOM_RANDOM_NUM = Random(Clock.System.now().toEpochMilliseconds())

        val users = listOf(
            User(
                kind = Kind.USER,
                userid = RandomString.make(16),
                handle = "handle_test1_${USER_HANDLE_RANDOM_NUM.nextInt(99)}",
                displayname = "Test 1",
                pictureurl = "http://www.thepresidentshalloffame.com/media/reviews/photos/original/a9/c7/a6/44-1-george-washington-18-1549729902.jpg",
                profileurl = "http://www.thepresidentshalloffame.com/1-george-washington"
            ),
            User(
                kind = Kind.USER,
                userid = RandomString.make(16),
                handle = "handle_test2_${USER_HANDLE_RANDOM_NUM.nextInt(99)}",
                displayname = "Test 2",
                pictureurl = "http://www.thepresidentshalloffame.com/media/reviews/photos/original/a9/c7/a6/44-1-george-washington-18-1549729902.jpg",
                profileurl = "http://www.thepresidentshalloffame.com/1-george-washington"
            ),
            User(
                kind = Kind.USER,
                userid = RandomString.make(16),
                handle = "handle_test3_${USER_HANDLE_RANDOM_NUM.nextInt(99)}",
                displayname = "Test 3",
                pictureurl = "http://www.thepresidentshalloffame.com/media/reviews/photos/original/a9/c7/a6/44-1-george-washington-18-1549729902.jpg",
                profileurl = "http://www.thepresidentshalloffame.com/1-george-washington"
            ),
            User(
                kind = Kind.USER,
                userid = RandomString.make(16),
                handle = "handle_test4_${USER_HANDLE_RANDOM_NUM.nextInt(99)}",
                displayname = "Test 4",
                pictureurl = "http://www.thepresidentshalloffame.com/media/reviews/photos/original/a9/c7/a6/44-1-george-washington-18-1549729902.jpg",
                profileurl = "http://www.thepresidentshalloffame.com/1-george-washington"
            )
        )

        var _chatRooms: List<ChatRoom>? = null
        fun chatRooms(appId: String): List<ChatRoom> =
            if (_chatRooms != null) _chatRooms!!
            else listOf(
                ChatRoom(
                    kind = Kind.ROOM,
                    id = RandomString.make(16),
                    appid = appId,
                    ownerid = null,
                    name = "Test Chat Room 1",
                    description = "This is a test chat room 1.",
                    customtype = null,
                    customid = "test-room-${CHATROOM_RANDOM_NUM.nextInt(999_999_999)}",
                    custompayload = null,
                    customtags = listOf(),
                    customfield1 = null,
                    customfield2 = null,
                    enableactions = true,
                    enableenterandexit = true,
                    open = true,
                    inroom = 1,
                    added = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    whenmodified = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    moderation = "post",
                    maxreports = 0L,
                    enableprofanityfilter = true,
                    delaymessageseconds = 0L
                ),
                ChatRoom(
                    kind = Kind.ROOM,
                    id = RandomString.make(16),
                    appid = appId,
                    ownerid = null,
                    name = "Test Chat Room 2",
                    description = "This is a test chat room 2.",
                    customtype = null,
                    customid = "test-room-${CHATROOM_RANDOM_NUM.nextInt(999_999_999)}",
                    custompayload = null,
                    customtags = listOf(),
                    customfield1 = null,
                    customfield2 = null,
                    enableactions = false,
                    enableenterandexit = false,
                    open = false,
                    inroom = 1,
                    added = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    whenmodified = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    moderation = "post",
                    maxreports = 0L,
                    enableprofanityfilter = false,
                    delaymessageseconds = 0L
                ),
                ChatRoom(
                    kind = Kind.ROOM,
                    id = RandomString.make(16),
                    appid = appId,
                    ownerid = null,
                    name = "Test Chat Room 3",
                    description = "This is a test chat room 3.",
                    customtype = null,
                    customid = "test-room-${CHATROOM_RANDOM_NUM.nextInt(999_999_999)}",
                    custompayload = null,
                    customtags = listOf(),
                    customfield1 = null,
                    customfield2 = null,
                    enableactions = true,
                    enableenterandexit = true,
                    open = false,
                    inroom = 1,
                    added = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    whenmodified = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    moderation = "post",
                    maxreports = 0L,
                    enableprofanityfilter = false,
                    delaymessageseconds = 0L
                ),
                ChatRoom(
                    kind = Kind.ROOM,
                    id = RandomString.make(16),
                    appid = appId,
                    ownerid = null,
                    name = "Test Chat Room 4",
                    description = "This is a test chat room 4.",
                    customtype = null,
                    customid = "test-room-${CHATROOM_RANDOM_NUM.nextInt(999_999_999)}",
                    custompayload = null,
                    customtags = listOf(),
                    customfield1 = null,
                    customfield2 = null,
                    enableactions = false,
                    enableenterandexit = false,
                    open = true,
                    inroom = 1,
                    added = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    whenmodified = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    moderation = "post",
                    maxreports = 0L,
                    enableprofanityfilter = true,
                    delaymessageseconds = 0L
                )
            )
    }

}