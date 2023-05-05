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
import com.sportstalk.sdk.model.chat.moderation.ApproveMessageRequest
import com.sportstalk.sdk.model.chat.moderation.ListMessagesNeedingModerationResponse
import com.sportstalk.sdk.model.reactions.Reaction
import com.sportstalk.sdk.model.reactions.ReactionType
import com.sportstalk.sdk.model.reports.Report
import com.sportstalk.sdk.model.reports.ReportType
import com.sportstalk.sdk.model.user.CreateUpdateUserRequest
import com.sportstalk.sdk.model.user.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import kotlinx.datetime.Clock
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlin.random.Random
import kotlin.test.*

class ChatClientTest {

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
            endpoint = "https://api.sportstalk247.com/api/v3",
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
    fun `0-ERROR-403 - Request is not authorized with a token`() = testScope.runTest {
        val userCaseChatService = ServiceFactory.Chat.get(
            config.copy(
                apiToken = "not-a-valid-auth-api-token"
            )
        )

        // GIVEN
        val testInputRequest = CreateChatRoomRequest(
            /*userid = "NON-Existing-User-ID"*/
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                userCaseChatService.createRoom(request = testInputRequest)
            }
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
            assertTrue { err.code == 401 }
        }

    }

    @Test
    fun `A - Create Room`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.chatRooms(config.appId).first()
        val testInputRequest = CreateChatRoomRequest(
            name = testExpectedData.name!!,
            customid = testExpectedData.customid,
            description = testExpectedData.description,
            moderation = testExpectedData.moderation,
            enableactions = testExpectedData.enableactions,
            enableenterandexit = testExpectedData.enableenterandexit,
            enableprofanityfilter = testExpectedData.enableprofanityfilter,
            enableautoexpiresessions = testExpectedData.enableautoexpiresessions,
            delaymessageseconds = testExpectedData.delaymessageseconds,
            roomisopen = testExpectedData.open,
            maxreports = testExpectedData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testActualResult: ChatRoom? = null
        try {
            val testExpectedResult = ChatRoom(
                kind = Kind.ROOM,
                appid = testExpectedData.appid,
                name = testExpectedData.name,
                customid = testExpectedData.customid,
                description = testExpectedData.description,
                moderation = testExpectedData.moderation,
                enableactions = testExpectedData.enableactions,
                enableenterandexit = testExpectedData.enableenterandexit,
                enableprofanityfilter = testExpectedData.enableprofanityfilter,
                enableautoexpiresessions = testExpectedData.enableautoexpiresessions,
                delaymessageseconds = testExpectedData.delaymessageseconds,
                open = testExpectedData.open,
                maxreports = testExpectedData.maxreports
            )

            // WHEN
            testActualResult = chatService.createRoom(
                request = testInputRequest
            )

            // THEN
            println(
                "`Create Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.appid == testExpectedResult.appid }
            assertTrue { testActualResult.name == testExpectedResult.name }
            assertTrue { testActualResult.customid == testExpectedResult.customid }
            assertTrue { testActualResult.description == testExpectedResult.description }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
            assertTrue { testActualResult.enableactions == testExpectedResult.enableactions }
            assertTrue { testActualResult.enableenterandexit == testExpectedResult.enableenterandexit }
            assertTrue { testActualResult.enableautoexpiresessions == testExpectedResult.enableautoexpiresessions }
            assertTrue { testActualResult.enableprofanityfilter == testExpectedResult.enableprofanityfilter }
            assertTrue { testActualResult.delaymessageseconds == testExpectedResult.delaymessageseconds }
            assertTrue { testActualResult.open == testExpectedResult.open }
            assertTrue { testActualResult.maxreports == testExpectedResult.maxreports }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testActualResult?.id)
        }
    }

    @Test
    fun `A-ERROR-404-User-not-found - Create Room`() = testScope.runTest {
        // GIVEN
        val testInputRequest = CreateChatRoomRequest(
            userid = "NON-Existing-User-ID",
            name = "Test Chat Room ${TestData.CHATROOM_RANDOM_NUM.nextInt(999_999_999)}",
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.createRoom(request = testInputRequest)
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-User-not-found - Create Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified ownerid ${testInputRequest.userid!!} was not found" }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `A-1 - Create Room With Custom Tags`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.chatRooms(config.appId).first()
        val testInputCustomTags = listOf("messenger", "whatsapp")

        val testInputRequest = CreateChatRoomRequest(
            name = testExpectedData.name!!,
            customid = testExpectedData.customid,
            description = testExpectedData.description,
            moderation = testExpectedData.moderation,
            enableactions = testExpectedData.enableactions,
            enableenterandexit = testExpectedData.enableenterandexit,
            enableprofanityfilter = testExpectedData.enableprofanityfilter,
            enableautoexpiresessions = testExpectedData.enableautoexpiresessions,
            delaymessageseconds = testExpectedData.delaymessageseconds,
            roomisopen = testExpectedData.open,
            maxreports = testExpectedData.maxreports,
            customtags = testInputCustomTags
        )

        var testActualResult: ChatRoom? = null
        try {
            val testExpectedResult = ChatRoom(
                kind = Kind.ROOM,
                appid = testExpectedData.appid,
                name = testExpectedData.name,
                customid = testExpectedData.customid,
                description = testExpectedData.description,
                moderation = testExpectedData.moderation,
                enableactions = testExpectedData.enableactions,
                enableenterandexit = testExpectedData.enableenterandexit,
                enableprofanityfilter = testExpectedData.enableprofanityfilter,
                enableautoexpiresessions = testExpectedData.enableautoexpiresessions,
                delaymessageseconds = testExpectedData.delaymessageseconds,
                open = testExpectedData.open,
                maxreports = testExpectedData.maxreports,
                customtags = testInputCustomTags
            )

            // WHEN
            testActualResult = chatService.createRoom(
                request = testInputRequest
            )

            // THEN
            println(
                "`Create Room With Custom Tags`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.appid == testExpectedResult.appid }
            assertTrue { testActualResult.name == testExpectedResult.name }
            assertTrue { testActualResult.customid == testExpectedResult.customid }
            assertTrue { testActualResult.description == testExpectedResult.description }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
            assertTrue { testActualResult.enableactions == testExpectedResult.enableactions }
            assertTrue { testActualResult.enableenterandexit == testExpectedResult.enableenterandexit }
            assertTrue { testActualResult.enableautoexpiresessions == testExpectedResult.enableautoexpiresessions }
            assertTrue { testActualResult.enableprofanityfilter == testExpectedResult.enableprofanityfilter }
            assertTrue { testActualResult.delaymessageseconds == testExpectedResult.delaymessageseconds }
            assertTrue { testActualResult.open == testExpectedResult.open }
            assertTrue { testActualResult.maxreports == testExpectedResult.maxreports }
            assertTrue { testActualResult.customtags == testExpectedResult.customtags }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testActualResult?.id)
        }

    }

    @Test
    fun `B-1 - Get Room Details`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()
        val testInputRequest = CreateChatRoomRequest(
            name = testData.name!!,
            customid = testData.customid,
            description = testData.description,
            moderation = testData.moderation,
            enableactions = testData.enableactions,
            enableenterandexit = testData.enableenterandexit,
            enableprofanityfilter = testData.enableprofanityfilter,
            enableautoexpiresessions = testData.enableautoexpiresessions,
            delaymessageseconds = testData.delaymessageseconds,
            roomisopen = testData.open,
            maxreports = testData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testInputRequest)

            val testExpectedResult = testCreatedChatRoomData.copy()

            // WHEN
            val testActualResult = chatService.getRoomDetails(
                chatRoomId = testCreatedChatRoomData.id!!
            )

            // THEN
            println(
                "`Get Room Details`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.name == testExpectedResult.name }
            assertTrue { testActualResult.customid == testExpectedResult.customid }
            assertTrue { testActualResult.description == testExpectedResult.description }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
            assertTrue { testActualResult.enableactions == testExpectedResult.enableactions }
            assertTrue { testActualResult.enableenterandexit == testExpectedResult.enableenterandexit }
            assertTrue { testActualResult.enableprofanityfilter == testExpectedResult.enableprofanityfilter }
            assertTrue { testActualResult.delaymessageseconds == testExpectedResult.delaymessageseconds }
            assertTrue { testActualResult.open == testExpectedResult.open }
            assertTrue { testActualResult.maxreports == testExpectedResult.maxreports }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }

    }

    @Test
    fun `B-ERROR-404 - Get Room Details`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.getRoomDetails(testInputRoomId)
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Get Room Details`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomId was not found." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `B-2 -  Get Room Extended Details Batch`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()
        val testInputRequest = CreateChatRoomRequest(
            name = testData.name!!,
            customid = testData.customid,
            description = testData.description,
            moderation = testData.moderation,
            enableactions = testData.enableactions,
            enableenterandexit = testData.enableenterandexit,
            enableprofanityfilter = testData.enableprofanityfilter,
            enableautoexpiresessions = testData.enableautoexpiresessions,
            delaymessageseconds = testData.delaymessageseconds,
            roomisopen = testData.open,
            maxreports = testData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testInputRequest)

            delay(200L)

            // WHEN
            val testActualResult = chatService.getRoomDetailsExtendedBatch(
                entityTypes = listOf(
                    RoomDetailEntityType.ROOM,
                    RoomDetailEntityType.NUM_PARTICIPANTS,
                    RoomDetailEntityType.LAST_MESSAGE_TIME
                ),
                roomIds = listOf(testCreatedChatRoomData.id!!)
            )

            // THEN
            println(
                "`Get Room Extended Details Batch`() -> testActualResult = \n" +
                        json.encodeToString(
                            GetRoomDetailsExtendedBatchResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.ROOM_EXTENDED_DETAILS }
            assertTrue { testActualResult.details.first().room != null }
            assertTrue { testActualResult.details.first().inroom != null }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }

    }

    @Test
    fun `B-ERROR-404 - Get Room Extended Details Batch`() = testScope.runTest {
        // GIVEN

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.getRoomDetailsExtendedBatch(
                    entityTypes = listOf(
                        RoomDetailEntityType.ROOM,
                        RoomDetailEntityType.NUM_PARTICIPANTS,
                        RoomDetailEntityType.LAST_MESSAGE_TIME
                    )
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Get Room Extended Details Batch`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "You must specify at least one roomid or customid" }
            assertTrue { err.code == 400 }

        }

    }

    @Test
    fun `B-3 - Get Room Details - By Custom ID`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(
                request = CreateChatRoomRequest(
                    name = testData.name!!,
                    customid = testData.customid,
                    description = testData.description,
                    moderation = testData.moderation,
                    enableactions = testData.enableactions,
                    enableenterandexit = testData.enableenterandexit,
                    enableprofanityfilter = testData.enableprofanityfilter,
                    enableautoexpiresessions = testData.enableautoexpiresessions,
                    delaymessageseconds = testData.delaymessageseconds,
                    roomisopen = testData.open,
                    maxreports = testData.maxreports,
                    customtags = listOf("tagA", "tagB")
                )
            )

            val testExpectedResult = testCreatedChatRoomData.copy()

            // WHEN
            val testActualResult = chatService.getRoomDetailsByCustomId(
                chatRoomCustomId = testCreatedChatRoomData.customid!!
            )

            // THEN
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.name == testExpectedResult.name }
            assertTrue { testActualResult.description == testExpectedResult.description }
            assertTrue { testActualResult.customid == testExpectedResult.customid }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }
    }

    @Test
    fun `B-ERROR-404 - Get Room Details - By Custom ID`() = testScope.runTest {
        // GIVEN
        val testInputCustomRoomId = "NON-Existing-Custom-Room-ID"

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.getRoomDetailsByCustomId(testInputCustomRoomId)
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Get Room Details - By Custom ID`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomId was not found." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `C - Delete Room`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()
        val testInputRequest = CreateChatRoomRequest(
            name = testData.name!!,
            customid = testData.customid,
            description = testData.description,
            moderation = testData.moderation,
            enableactions = testData.enableactions,
            enableenterandexit = testData.enableenterandexit,
            enableprofanityfilter = testData.enableprofanityfilter,
            enableautoexpiresessions = testData.enableautoexpiresessions,
            delaymessageseconds = testData.delaymessageseconds,
            roomisopen = testData.open,
            maxreports = testData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testInputRequest)

            val testExpectedResult = DeleteChatRoomResponse(
                kind = Kind.DELETED_ROOM,
                deletedEventsCount = 0,
                room = testCreatedChatRoomData
            )

            // WHEN
            val testActualResult = chatService.deleteRoom(
                chatRoomId = testCreatedChatRoomData.id!!
            )

            // THEN
            println(
                "`Delete Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteChatRoomResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.room == testExpectedResult.room }
        } catch (err: SportsTalkException) {
            err.printStackTrace()

            deleteTestChatRooms(testCreatedChatRoomData?.id)

            fail(err.message)
        }
    }

    @Test
    fun `C-ERROR-404 - Delete Room`() = testScope.runTest {
        // GIVEN
        val testInputCustomRoomId = "NON-Existing-Room-ID"

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.deleteRoom(testInputCustomRoomId)
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Delete Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specifed room [${testInputCustomRoomId}] does not exist within the specified application." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `D - Update Room`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(
                request = CreateChatRoomRequest(
                    name = testData.name!!,
                    customid = testData.customid,
                    description = testData.description,
                    moderation = testData.moderation,
                    enableactions = testData.enableactions,
                    enableenterandexit = testData.enableenterandexit,
                    enableprofanityfilter = testData.enableprofanityfilter,
                    enableautoexpiresessions = testData.enableautoexpiresessions,
                    delaymessageseconds = testData.delaymessageseconds,
                    roomisopen = testData.open,
                    maxreports = testData.maxreports,
                    customtags = listOf("tagA", "tagB")
                )
            )

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputRequest = UpdateChatRoomRequest(
                name = "${testData.name!!}-updated",
                customid = "${testData.customid}-updated(${Clock.System.now().toEpochMilliseconds()})",
                description = "${testData.description}-updated",
                enableactions = !testData.enableactions!!,
                enableenterandexit = !testData.enableenterandexit!!,
                enableautoexpiresessions = !testData.enableautoexpiresessions!!,
                maxreports = 30L
            )

            val testExpectedResult = testCreatedChatRoomData.copy(
                name = testInputRequest.name,
                customid = testInputRequest.customid,
                description = testInputRequest.description,
                enableactions = testInputRequest.enableactions,
                enableenterandexit = testInputRequest.enableenterandexit,
                enableautoexpiresessions = testInputRequest.enableautoexpiresessions,
                maxreports = testInputRequest.maxreports
            )

            // WHEN
            val testActualResult = chatService.updateRoom(
                chatRoomId = testInputChatRoomId,
                request = testInputRequest
            )

            // THEN
            println(
                "`Update Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.name == testExpectedResult.name }
            assertTrue { testActualResult.customid == testExpectedResult.customid }
            assertTrue { testActualResult.description == testExpectedResult.description }
            assertTrue { testActualResult.enableactions == testExpectedResult.enableactions }
            assertTrue { testActualResult.enableenterandexit == testExpectedResult.enableenterandexit }
            assertTrue { testActualResult.enableautoexpiresessions == testExpectedResult.enableautoexpiresessions }
            assertTrue { testActualResult.maxreports == testExpectedResult.maxreports }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }

    }

    @Test
    fun `D-ERROR-404 - Update Room`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testData = TestData.chatRooms(config.appId).first()
        val testInputRequest = UpdateChatRoomRequest(
            name = "${testData.name!!}-updated",
            customid = "${testData.customid}-updated(${Clock.System.now().toEpochMilliseconds()})",
            description = "${testData.description}-updated",
            enableactions = !testData.enableactions!!,
            enableenterandexit = !testData.enableenterandexit!!,
            maxreports = 30L
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.updateRoom(
                    chatRoomId = testInputRoomId,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Update Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomid could not be found: $testInputRoomId" }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `E - Touch Session`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${RandomString.make(6)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle!!
            )

            // Attempt join room
            chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testInputRequest
            )

            val testExpectedResult = ChatSubscription(
                kind = Kind.CHAT_SUBSCRIPTION,
                id = "${config.appId}|${testCreatedChatRoomData.id ?: ""}|${testCreatedUserData.userid ?: ""}",
                roomid = testCreatedChatRoomData.id,
                roomcustomid = testCreatedChatRoomData.customid,
                userid = testCreatedUserData.userid,
                roomname = testCreatedChatRoomData.name,
                roomcustomtags = testCreatedChatRoomData.customtags
            )

            // WHEN
            // Perform touch session
            val testActualResult = chatService.touchSession(
                chatRoomId = testInputChatRoomId,
                userId = testCreatedUserData.userid!!
            )

            // THEN
            println("`Touch Session`() -> testActualResult = \n" +
                    json.encodeToString(
                        ChatSubscription.serializer(),
                        testActualResult
                    )
            )

            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.roomid == testExpectedResult.roomid }
            assertTrue { testActualResult.roomcustomid == testExpectedResult.roomcustomid }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.roomname == testExpectedResult.roomname }
            assertTrue { testActualResult.roomcustomtags == testExpectedResult.roomcustomtags }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `F - List Rooms`() = testScope.runTest {
        // GIVEN
        val testData = TestData.chatRooms(config.appId).first()
        val testInputRequest = CreateChatRoomRequest(
            name = testData.name!!,
            customid = testData.customid,
            description = testData.description,
            moderation = testData.moderation,
            enableactions = testData.enableactions,
            enableenterandexit = testData.enableenterandexit,
            enableprofanityfilter = testData.enableprofanityfilter,
            enableautoexpiresessions = testData.enableautoexpiresessions,
            delaymessageseconds = testData.delaymessageseconds,
            roomisopen = testData.open,
            maxreports = testData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testInputRequest)

            val testExpectedResult = ListRoomsResponse(
                kind = Kind.ROOM_LIST,
                rooms = listOf(testCreatedChatRoomData)
            )

            // WHEN
            val testActualResult = chatService.listRooms(
                limit = 100/*,
                cursor = testCreatedChatRoomData.id!!*/
            )

            // THEN
            println(
                "`List Rooms`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListRoomsResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.rooms.containsAll(testExpectedResult.rooms) }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }

    }

    @Test
    fun `G - Join Room - Authenticated User`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${RandomString.make(6)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testExpectedResult = JoinChatRoomResponse(
                kind = Kind.JOIN_ROOM,
                user = testCreatedUserData,
                room = testCreatedChatRoomData.copy(inroom = testCreatedChatRoomData.inroom!! + 1),
                subscription = ChatSubscription(
                    kind = Kind.CHAT_SUBSCRIPTION,
                    id = "${config.appId}|${testCreatedChatRoomData.id ?: ""}|${testCreatedUserData.userid ?: ""}",
                    roomid = testCreatedChatRoomData.id,
                    roomcustomid = testCreatedChatRoomData.customid,
                    userid = testCreatedUserData.userid,
                    roomname = testCreatedChatRoomData.name,
                    roomcustomtags = testCreatedChatRoomData.customtags,
                )
            )

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle!!
            )

            // WHEN
            val testActualResult = chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testInputRequest
            )

            // THEN
            println(
                "`Join Room - Authenticated User`() -> testActualResult = \n" +
                        json.encodeToString(
                            JoinChatRoomResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.user?.userid == testExpectedResult.user?.userid }
            assertTrue { testActualResult.user?.handle == testExpectedResult.user?.handle }
            assertTrue { testActualResult.room?.id == testExpectedResult.room?.id }
            assertTrue { testActualResult.room?.name == testExpectedResult.room?.name }
            assertTrue { testActualResult.room?.description == testExpectedResult.room?.description }
            // Assert user room subscription
            assertTrue { testActualResult.subscription?.id == testExpectedResult.subscription?.id }
            assertTrue { testActualResult.subscription?.roomid == testExpectedResult.subscription?.roomid }
            assertTrue { testActualResult.subscription?.roomcustomid == testExpectedResult.subscription?.roomcustomid }
            assertTrue { testActualResult.subscription?.userid == testExpectedResult.subscription?.userid }
            assertTrue { testActualResult.subscription?.roomname == testExpectedResult.subscription?.roomname }
            assertTrue { testActualResult.subscription?.roomcustomtags == testExpectedResult.subscription?.roomcustomtags }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

//  Join Room - Anonymous User No Longer Supported
//    @Test
//    fun `G) Join Room - Anonymous User`() = runBlocking {
//        // GIVEN
//        val testChatRoomData = TestData.chatRooms(config.appId).first()
//        val testCreateChatRoomInputRequest = CreateChatRoomRequest(
//                name = testChatRoomData.name!!,
//                customid = testChatRoomData.customid,
//                description = testChatRoomData.description,
//                moderation = testChatRoomData.moderation,
//                enableactions = testChatRoomData.enableactions,
//                enableenterandexit = testChatRoomData.enableenterandexit,
//                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
//                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
//                delaymessageseconds = testChatRoomData.delaymessageseconds,
//                roomisopen = testChatRoomData.open,
//                maxreports = testChatRoomData.maxreports
//        )
//        // Should create a test chat room first
//        val testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)
//
//        val testExpectedResult = JoinChatRoomResponse(
//                kind = Kind.JOIN_ROOM,
//                user = null,
//                room = null
//        )
//        // WHEN
//        val testActualResult = chatService.joinRoom(
//                chatRoomIdOrLabel = testCreatedChatRoomData.id!!
//        )
//
//        // THEN
//        println(
//                "`Join Room - Anonymous User`() -> testActualResult = \n" +
//                        json.encodeToString(
//                                JoinChatRoomResponse.serializer(),
//                                testActualResult
//                        )
//        )
//
//        assertTrue { testActualResult.kind == testExpectedResult.kind }
//        assertTrue { testActualResult.user == null }
//        assertTrue { testActualResult.room == testCreatedChatRoomData }
//
//        // Also, assert that ChatRoomEventCursor is currently stored
//        assertTrue { testActualResult.eventscursor?.cursor == chatService.chatRoomEventCursor[testCreatedChatRoomData.id!!] }
//
//        // Perform Delete Test Chat Room
//        deleteTestChatRooms(testCreatedChatRoomData.id)
//    }

    @Test
    fun `G-ERROR-404-Room-not-found - Join Room`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testInputRequest = JoinChatRoomRequest(
            userid = "non-existing-user"
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.joinRoom(
                    chatRoomId = testInputRoomId,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-Room-not-found - Join Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomid '${testInputRoomId}' was not found." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `H - Join Room - By Custom ID`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${RandomString.make(6)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testRoomData = TestData.chatRooms(config.appId).first()
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(
                request = CreateChatRoomRequest(
                    name = testRoomData.name!!,
                    customid = testRoomData.customid,
                    description = testRoomData.description,
                    moderation = testRoomData.moderation,
                    enableactions = testRoomData.enableactions,
                    enableenterandexit = testRoomData.enableenterandexit,
                    enableprofanityfilter = testRoomData.enableprofanityfilter,
                    enableautoexpiresessions = testRoomData.enableautoexpiresessions,
                    delaymessageseconds = testRoomData.delaymessageseconds,
                    roomisopen = testRoomData.open,
                    maxreports = testRoomData.maxreports,
                    customtags = listOf("tagA", "tagB")
                )
            )

            val testExpectedResult = JoinChatRoomResponse(
                kind = Kind.JOIN_ROOM,
                user = testCreatedUserData,
                room = testCreatedChatRoomData.copy(inroom = testCreatedChatRoomData.inroom!! + 1),
                subscription = ChatSubscription(
                    kind = Kind.CHAT_SUBSCRIPTION,
                    id = "${config.appId}|${testCreatedChatRoomData.id ?: ""}|${testCreatedUserData.userid ?: ""}",
                    roomid = testCreatedChatRoomData.id,
                    roomcustomid = testCreatedChatRoomData.customid,
                    userid = testCreatedUserData.userid,
                    roomname = testCreatedChatRoomData.name,
                    roomcustomtags = testCreatedChatRoomData.customtags,
                )
            )

            val testInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle
            )

            // WHEN
            val testActualResult = chatService.joinRoomByCustomId(
                chatRoomCustomId = testCreatedChatRoomData.customid!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Join Room - By Custom ID`() -> testActualResult = \n" +
                        json.encodeToString(
                            JoinChatRoomResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.user?.userid == testExpectedResult.user?.userid }
            assertTrue { testActualResult.room?.customid == testCreatedChatRoomData.customid }

            // Also, assert that ChatRoomEventCursor is currently stored
            assertTrue {
                testActualResult.eventscursor?.cursor?.takeIf { it.isNotEmpty() } ==
                        chatService.getChatRoomEventUpdateCursor(testCreatedChatRoomData.id!!)
            }

            // Assert user room subscription
            assertTrue { testActualResult.subscription?.id == testExpectedResult.subscription?.id }
            assertTrue { testActualResult.subscription?.roomid == testExpectedResult.subscription?.roomid }
            assertTrue { testActualResult.subscription?.roomcustomid == testExpectedResult.subscription?.roomcustomid }
            assertTrue { testActualResult.subscription?.userid == testExpectedResult.subscription?.userid }
            assertTrue { testActualResult.subscription?.roomname == testExpectedResult.subscription?.roomname }
            assertTrue { testActualResult.subscription?.roomcustomtags == testExpectedResult.subscription?.roomcustomtags }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `I - List Room Participants`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${RandomString.make(6)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testInputJoinRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )

            // WHEN
            val testJoinChatRoomData = chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testInputJoinRequest
            )

            val testExpectedResult = ListChatRoomParticipantsResponse(
                kind = Kind.CHAT_LIST_PARTICIPANTS,
                participants = listOf(
                    ChatRoomParticipant(
                        kind = Kind.CHAT_PARTICIPANT,
                        user = testJoinChatRoomData.user!!
                    )
                )
            )

            val testInputChatRoomId = testJoinChatRoomData.room?.id!!
            val testInputLimit = 10

            // WHEN
            delay(500)
            advanceUntilIdle()
            val testActualResult = chatService.listRoomParticipants(
                chatRoomId = testInputChatRoomId,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Room Participants`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListChatRoomParticipantsResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.participants.isNotEmpty() }
            assertTrue { testActualResult.participants.first().kind == testExpectedResult.participants.first().kind }
            assertTrue { testActualResult.participants.first().user!!.userid == testExpectedResult.participants.first().user!!.userid }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `I-ERROR-404 - Join Room`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testInputRequest = JoinChatRoomRequest(
            userid = "non-existing-user"
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.joinRoom(
                    chatRoomId = testInputRoomId,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-Room-not-found - Join Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomid '${testInputRoomId}' was not found." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `J - List User Subscribed Rooms`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${RandomString.make(6)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )

            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testInputJoinRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )

            // WHEN
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testInputJoinRequest
            )

            val expectedChatSubscriptionAndStatus = ListUserSubscribedRoomsResponse.Data(
                kind = Kind.CHAT_SUBSCRIPTION_AND_STATUS,
                subscription = ChatSubscription(
                    kind = Kind.CHAT_SUBSCRIPTION,
                    roomid = testCreatedChatRoomData.id,
                    roomcustomid = testCreatedChatRoomData.customid,
                    userid = testCreatedUserData.userid,
                    roomname = testCreatedChatRoomData.name,
                    roomcustomtags = testCreatedChatRoomData.customtags
                ),
                roomstatus = ListUserSubscribedRoomsResponse.RoomStatus(
                    kind = Kind.CHAT_ROOM_STATUS,
                    messagecount = 1L,
                    participantcount = 1L,
                ),
            )
            val testExpectedResult = ListUserSubscribedRoomsResponse(
                kind = Kind.LIST_USER_ROOM_SUBSCRIPTIONS,
                subscriptions = listOf(
                    expectedChatSubscriptionAndStatus
                )
            )

            val testInputUserId = testCreatedUserData.userid!!
            val testInputLimit = 10

            // WHEN
            delay(500)
            advanceUntilIdle()
            val testActualResult = chatService.listUserSubscribedRooms(
                userid = testInputUserId,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List User Subscribed Rooms`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListUserSubscribedRoomsResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.subscriptions.isNotEmpty() }
            val actualChatSubscriptionAndStatus = testActualResult.subscriptions.first()
            assertTrue { actualChatSubscriptionAndStatus.kind == expectedChatSubscriptionAndStatus.kind }
            assertTrue { actualChatSubscriptionAndStatus.subscription?.roomid == expectedChatSubscriptionAndStatus.subscription?.roomid }
            assertTrue { actualChatSubscriptionAndStatus.subscription?.roomcustomid == expectedChatSubscriptionAndStatus.subscription?.roomcustomid }
            assertTrue { actualChatSubscriptionAndStatus.subscription?.userid == expectedChatSubscriptionAndStatus.subscription?.userid }
            assertTrue { actualChatSubscriptionAndStatus.subscription?.roomname == expectedChatSubscriptionAndStatus.subscription?.roomname }
            assertTrue { actualChatSubscriptionAndStatus.subscription?.roomcustomtags == expectedChatSubscriptionAndStatus.subscription?.roomcustomtags }
            assertTrue { actualChatSubscriptionAndStatus.roomstatus?.kind == expectedChatSubscriptionAndStatus.roomstatus?.kind }
            /*assertTrue { actualChatSubscriptionAndStatus.roomstatus?.messagecount == expectedChatSubscriptionAndStatus.roomstatus?.messagecount }*/
            assertTrue { actualChatSubscriptionAndStatus.roomstatus?.participantcount != null/*== expectedChatSubscriptionAndStatus.roomstatus?.participantcount*/ }
            assertTrue { actualChatSubscriptionAndStatus.roomstatus?.newestmessage != null }        // "[user] has entered the room"
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `K - Exit a Room`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!

            // WHEN
            chatService.exitRoom(
                chatRoomId = testInputChatRoomId,
                userId = testInputUserId
            )

            // THEN
            println(
                "`Exit a Room`() -> testActualResult"
            )

            assertTrue { true }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `K-ERROR-404 - Exit a Room`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testInputRequest = JoinChatRoomRequest(
            userid = "non-existing-user"
        )

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.joinRoom(
                    chatRoomId = testInputRoomId,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-Room-not-found - Join Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified roomid '${testInputRoomId}' was not found." }
            assertTrue { err.code == 404 }
        }
    }

    @Test
    fun `L - Get Updates`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testSendMessageInputRequest
            ).speech!!

            val testExpectedResult = GetUpdatesResponse(
                kind = Kind.CHAT_LIST,
                /*cursor = "",*/
                more = false,
                itemcount = 1,
                events = listOf(testSendMessageData)
            )

            val testInputLimit = 10

            // WHEN
            val testActualResult = chatService.getUpdates(
                chatRoomId = testCreatedChatRoomData.id!!,
                limit = testInputLimit/*,
                cursor = null*/
            )

            // THEN
            println(
                "`Get Updates`() -> testActualResult = \n" +
                        json.encodeToString(
                            GetUpdatesResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.itemcount!! >= testExpectedResult.itemcount!! }
            assertTrue { testActualResult.more == testExpectedResult.more }
            assertTrue { testActualResult.events.size <= testInputLimit }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.body == testSendMessageData.body
                            && ev.eventtype == testSendMessageData.eventtype
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `L-ERROR-404 - Get Updates`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.getUpdates(
                    chatRoomId = testInputRoomId
                )
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            println(
                "`ERROR-404 - Get Updates`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified room was not found." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `L-1 - All Event Updates`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = testUserData.userid!!,
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )

            // Test Created User Should join test created chat room
            delay(300)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testSendMessageInputRequest
            ).speech!!

            val testExpectedResult = GetUpdatesResponse(
                kind = Kind.CHAT_LIST,
                /*cursor = "",*/
                more = false,
                itemcount = 1,
                events = listOf(testSendMessageData)
            )

            val chatRoomId = testCreatedChatRoomData.id!!
            chatService.startListeningToChatUpdates(chatRoomId)

            // WHEN
            val job = chatService.allEventUpdates(
                chatRoomId = chatRoomId,
                frequency = 1000
            )
                .take(1)
                .withIndex()
                .onEach { (index, testActualResult) ->
                    println(
                        "`All Event Updates[$index]`() -> response = \n" +
                                json.encodeToString(
                                    ListSerializer(ChatEvent.serializer()),
                                    testActualResult/*.toTypedArray()*/
                                )
                    )

                    assertTrue { testActualResult.size == testExpectedResult.itemcount!!.toInt() }
                }
                .onCompletion {
                    chatService.stopListeningToChatUpdates(chatRoomId)
                }
                .launchIn(testScope)

            delay(4000)
            advanceUntilIdle()

            job.cancelAndJoin()
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `M - Message Is Reported`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputReportMessageRequest = ReportMessageRequest(
                reporttype = "abuse",
                userid = testCreatedUserData.userid!!
            )

            delay(1500)
            advanceUntilIdle()
            val reportMessageResponse = chatService.reportMessage(
                chatRoomId = testCreatedChatRoomData.id!!,
                eventId = testSendMessageData.id!!,
                request = testInputReportMessageRequest
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val messageIsReported = chatService.messageIsReported(
                which = reportMessageResponse,
                userid = testCreatedUserData.userid!!
            )

            // THEN
            println("`Message Is Reported`() -> messageIsReported = $messageIsReported")

            assertTrue { messageIsReported }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `N - Message Is Reacted To`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )

            // Test Created User Should send a message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputReaction = ReactionType.LIKE

            val testInputRequest = ReactToAMessageRequest(
                userid = testCreatedUserData.userid!!,
                reaction = testInputReaction,
                reacted = true
            )

            delay(1000)
            advanceUntilIdle()
            val reactToEventResponse = chatService.reactToEvent(
                chatRoomId = testCreatedChatRoomData.id!!,
                eventId = testSendMessageData.id!!,
                request = testInputRequest
            )
            println(
                "`Message Is Reacted To`() -> reactToEventResponse = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            reactToEventResponse
                        )
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val messageIsReactedTo = chatService.messageIsReactedTo(
                which = reactToEventResponse,
                userid = testCreatedUserData.userid!!,
                reaction = testInputReaction
            )

            // THEN
            println("`Message Is Reacted To`() -> messageIsReactedTo = $messageIsReactedTo")

            assertTrue { messageIsReactedTo }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `O - List Previous Events`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputLimit = 10
            val testExpectedResult = ListEvents(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val testActualResult = chatService.listPreviousEvents(
                chatRoomId = testInputChatRoomId,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Previous Events`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListEvents.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.roomid == testSendMessageData.roomid
                            && ev.eventtype == testSendMessageData.eventtype
                            && ev.body == testSendMessageData.body
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `P - Get Event By ID`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(1000)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(1000)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputChatEventId = testSendMessageData.id!!
            val testExpectedResult = testSendMessageData.copy()

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val testActualResult = chatService.getEventById(
                chatRoomId = testInputChatRoomId,
                eventId = testInputChatEventId
            )

            // THEN
            println(
                "`Get Event By ID`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.roomid == testExpectedResult.roomid }
            assertTrue { testActualResult.body == testExpectedResult.body }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `Q - Report User In Room`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testAnotherUserData = TestData.users.last()
            val testAnotherCreateUserInputRequest = CreateUpdateUserRequest(
                userid = RandomString.make(16),
                handle = "${testAnotherUserData.handle}_${Random.nextInt(100, 999)}",
                displayname = testAnotherUserData.displayname,
                pictureurl = testAnotherUserData.pictureurl,
                profileurl = testAnotherUserData.profileurl
            )
            // Should create ANOTHER test user first
            val testAnotherCreatedUserData =
                userService.createOrUpdateUser(request = testAnotherCreateUserInputRequest)

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testAnotherJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testAnotherCreatedUserData.userid!!
            )
            // Test Another Created User Should join test created chat room
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testAnotherJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testInputReporterId = testAnotherCreatedUserData.userid!!
            val testInputReportType = ReportType.ABUSE

            val testExpectedResult = testCreatedChatRoomData.copy()

            // WHEN
            val testActualResult = chatService.reportUserInRoom(
                chatRoomId = testInputChatRoomId,
                userid = testInputUserId,
                reporterid = testInputReporterId,
                reporttype = testInputReportType
            )

            // THEN
            println(
                "`Report User In Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.reportedusers?.any { report ->
                    report.userid == testInputUserId
                            && report.reportedbyuserid == testInputReporterId
                } == true
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `Q-ERROR-403 - Report User In Room`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        var testInputReporterId: String? = null
        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testAnotherUserData = TestData.users.last()
            val testAnotherCreateUserInputRequest = CreateUpdateUserRequest(
                userid = RandomString.make(16),
                handle = "${testAnotherUserData.handle}_${Random.nextInt(100, 999)}",
                displayname = testAnotherUserData.displayname,
                pictureurl = testAnotherUserData.pictureurl,
                profileurl = testAnotherUserData.profileurl
            )
            // Should create ANOTHER test user first
            val testAnotherCreatedUserData =
                userService.createOrUpdateUser(request = testAnotherCreateUserInputRequest)

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testAnotherJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testAnotherCreatedUserData.userid!!
            )
            // Test Another Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testAnotherJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            testInputReporterId = testAnotherCreatedUserData.userid!!
            val testInputReportType = ReportType.ABUSE

            // Should make sure that this user is shadow banned to ensure that the request is rejected, and that no changes will be made.
            delay(1000L)
            advanceUntilIdle()
            userService.setBanStatus(
                userId = testInputReporterId,
                applyeffect = true,
                expireseconds = 3_000L
            )

            // WHEN
            delay(1000L)
            advanceUntilIdle()
            chatService.reportUserInRoom(
                chatRoomId = testInputChatRoomId,
                userid = testInputUserId,
                reporterid = testInputReporterId,
                reporttype = testInputReportType
            )

            fail("Report User In Room operation must fail...")
        } catch (err: SportsTalkException) {
            // THEN
            println(
                "`ERROR-403) Report User In Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )

            assertTrue { err.code == 403 }
            assertTrue { err.message == "The user doing the reporting ($testInputReporterId) is suspended from talk experiences by Banned or Muted users." }
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `R - List Events History`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputLimit = 10
            val testExpectedResult = ListEvents(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val testActualResult = chatService.listEventsHistory(
                chatRoomId = testInputChatRoomId,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Events History`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListEvents.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.roomid == testSendMessageData.roomid
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `S - List Events By Type`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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
                command = "Yow Jessy, ANNOUNCEMENT how are you doin'?",
                userid = testCreatedUserData.userid!!,
                eventtype = EventType.ANNOUNCEMENT
            )
            // Test Created User Should send a message to the created chat room
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputEventtype = testSendMessageData.eventtype!!
            val testInputLimit = 10
            val testExpectedResult = ListEvents(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            val testActualResult = chatService.listEventsByType(
                chatRoomId = testInputChatRoomId,
                eventType = testInputEventtype,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Events By Type`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListEvents.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.roomid == testSendMessageData.roomid
                            && ev.eventtype == testSendMessageData.eventtype
                            && ev.body == testSendMessageData.body
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `S - List Events By Type - Custom Type`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputEventtype = EventType.CUSTOM
            val testInputCustomType = "specialcustomtype"

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, ANNOUNCEMENT how are you doin'?",
                userid = testCreatedUserData.userid!!,
                eventtype = testInputEventtype,
                customtype = testInputCustomType
            )
            // Test Created User Should send a message to the created chat room
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputLimit = 10
            val testExpectedResult = ListEvents(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            val testActualResult = chatService.listEventsByType(
                chatRoomId = testInputChatRoomId,
                eventType = testInputEventtype,
                customtype = testInputCustomType,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Events By Type - Custom Type`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListEvents.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.roomid == testSendMessageData.roomid
                            && ev.eventtype == testSendMessageData.eventtype
                            && ev.customtype == testInputCustomType
                            && ev.body == testSendMessageData.body
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `T - List Events By Timestamp`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputTimestampt = testSendMessageData.ts!!
            val testInputLimit = 0
            val testExpectedResult = ListEventsByTimestamp(
                kind = Kind.CHAT_LIST_BY_TIMESTAMP,
                events = listOf(testSendMessageData)
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = chatService.listEventsByTimestamp(
                chatRoomId = testInputChatRoomId,
                timestamp = testInputTimestampt,
                limitolder = testInputLimit,
                limitnewer = testInputLimit
            )

            // THEN
            println(
                "`List Events By Timestamp`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListEventsByTimestamp.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.kind == testSendMessageData.kind
                            && ev.roomid == testSendMessageData.roomid
                            && ev.eventtype == testSendMessageData.eventtype
                            && ev.body == testSendMessageData.body
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-1 - Execute Chat Command - Speech`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            val testExpectedResult = ExecuteChatCommandResponse(
                kind = Kind.CHAT_COMMAND,
                op = "speech",
                room = testCreatedChatRoomData,
                speech = ChatEvent(
                    kind = Kind.CHAT,
                    roomid = testCreatedChatRoomData.id,
                    body = testInputRequest.command,
                    eventtype = "speech",
                    userid = testCreatedUserData.userid,
                    user = testCreatedUserData
                ),
                action = null
            )

            // WHEN
            val testActualResult = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Execute Chat Command - Speech`() -> testActualResult = \n" +
                        json.encodeToString(
                            ExecuteChatCommandResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.op == testExpectedResult.op }
            assertTrue { testActualResult.speech?.kind == testExpectedResult.speech?.kind }
            assertTrue { testActualResult.speech?.roomid == testExpectedResult.speech?.roomid }
            assertTrue { testActualResult.speech?.body == testExpectedResult.speech?.body }
            assertTrue { testActualResult.speech?.eventtype == testExpectedResult.speech?.eventtype }
            assertTrue { testActualResult.speech?.userid == testExpectedResult.speech?.userid }
            assertTrue { testActualResult.speech?.user?.userid == testExpectedResult.speech?.user?.userid }
            assertTrue { testActualResult.action == testExpectedResult.action }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-2 - Execute Chat Command - Action`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData =
                userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = "${testChatRoomData.customid}-${Random.nextInt(100, 999)}",
                description = testChatRoomData.description,
                moderation = testChatRoomData.moderation,
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputRequest = ExecuteChatCommandRequest(
                // "/high5 {{handle}}"
                command = "/high5 ${testCreatedUserData.handle!!}",
                userid = testCreatedUserData.userid!!
            )
            val testExpectedResult = ExecuteChatCommandResponse(
                kind = Kind.CHAT_COMMAND,
                op = "action",
                room = testCreatedChatRoomData,
                speech = null,
                action = ChatEvent(
                    kind = Kind.CHAT,
                    roomid = testCreatedChatRoomData.id,
                    // "Test 1 gave Test 1 a high 5!"
                    body = "${testCreatedUserData.displayname!!} gave ${testCreatedUserData.displayname!!} a high 5!",
                    eventtype = "action",
                    userid = testCreatedUserData.userid,
                    user = testCreatedUserData
                )
            )

            // WHEN
            val testActualResult = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Execute Chat Command - Action`() -> testActualResult = \n" +
                        json.encodeToString(
                            ExecuteChatCommandResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.op == testExpectedResult.op }
            assertTrue { testActualResult.speech == null }
            assertTrue { testActualResult.action?.kind == testExpectedResult.action?.kind }
            assertTrue { testActualResult.action?.roomid == testExpectedResult.action?.roomid }
            assertTrue { testActualResult.action?.body == testExpectedResult.action?.body }
            assertTrue { testActualResult.action?.eventtype == testExpectedResult.action?.eventtype }
            assertTrue { testActualResult.action?.userid == testExpectedResult.action?.userid }
            assertTrue { testActualResult.action?.user?.userid == testExpectedResult.action?.user?.userid }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-3 - Execute Chat Command - Reply to a Message - Threaded`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!


            val testInputRequest = SendThreadedReplyRequest(
                body = "This is Jessy, replying to your greetings yow!!!",
                userid = testCreatedUserData.userid!!
            )
            val testExpectedResult = ChatEvent(
                kind = Kind.CHAT,
                roomid = testCreatedChatRoomData.id,
                body = testInputRequest.body,
                eventtype = "reply",
                userid = testCreatedUserData.userid,
                user = testCreatedUserData,
                replyto = testInitialSendMessage
            )

            // WHEN
            delay(1000L)
            advanceUntilIdle()
            val testActualResult = chatService.sendThreadedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Execute Chat Command - Reply to a Message - Threaded`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.roomid == testExpectedResult.roomid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.eventtype == testExpectedResult.eventtype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.user?.userid == testExpectedResult.user?.userid }
            assertTrue { testActualResult.replyto?.id == testExpectedResult.replyto?.id }
            assertTrue { testActualResult.replyto?.kind == testExpectedResult.replyto?.kind }
            assertTrue { testActualResult.replyto?.body == testExpectedResult.replyto?.body }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `U-4 - Execute Chat Command - Purge User Messages`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testUserID = RandomString.make(4)
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "handle_test1_$testUserID",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData = userService.createOrUpdateUser(request = testCreateUserInputRequest)

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )
            val testAdminJoinRoomInputRequest = JoinChatRoomRequest(
                userid = "admin"/*testCreatedAdminData.userid!!*/,
                handle = "admin",
                displayname = "admin",
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testAdminJoinRoomInputRequest
            )

            val testInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Send test message
            delay(300L)
            advanceUntilIdle()
            chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            val testExpectedResult = ExecuteChatCommandResponse(
                kind = Kind.API,
                // message = "The user's 1 messages were purged."
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = ExecuteChatCommandRequest(
                    command = "*purge ${testCreatedUserData.handle!!}",
                    userid = "admin"
                )
            )

            // THEN
            println(
                "`Execute Chat Command - Purge User Messages`() -> testActualResult = \n" +
                        json.encodeToString(
                            ExecuteChatCommandResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.message != null }

        } catch(err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-5 - Execute Chat Command - Admin Command`() = testScope.runTest {
        // TODO:: Admin password is hardcoded as "zola".
    }

    @Test
    fun `U-6 - Execute Chat Command - Admin - Delete All Events`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInputRequest = ExecuteChatCommandRequest(
                command = "*deleteallevents ${TestData.ADMIN_PASSWORD}",
                userid = "admin"/*testCreatedUserData.userid!!*/
            )
            val testExpectedResult = ExecuteChatCommandResponse(
                kind = Kind.CHAT_COMMAND,
                op = "admin",
                room = null,
                speech = null,
                action = null
            )

            // WHEN
            delay(300L)
            advanceUntilIdle()
            val testActualResult = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Execute Chat Command - Admin - Delete All Events`() -> testActualResult = \n" +
                        json.encodeToString(
                            ExecuteChatCommandResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.op == testExpectedResult.op }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-7 - Send Quoted Reply`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send an initial message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testInitialSendMessage = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!


            val testInputRequest = SendQuotedReplyRequest(
                userid = testCreatedUserData.userid!!,
                body = "This is Jessy, replying to your greetings yow!!!"
            )
            val testExpectedResult = ChatEvent(
                kind = /*"chat.event"*/Kind.CHAT,
                roomid = testCreatedChatRoomData.id,
                body = testInputRequest.body,
                eventtype = EventType.QUOTE,
                userid = testCreatedUserData.userid,
                user = testCreatedUserData,
                replyto = testInitialSendMessage
            )

            // WHEN
            delay(1000L)
            advanceUntilIdle()
            val testActualResult = chatService.sendQuotedReply(
                chatRoomId = testCreatedChatRoomData.id!!,
                replyTo = testInitialSendMessage.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Send Quoted Reply`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.roomid == testExpectedResult.roomid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.eventtype == testExpectedResult.eventtype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.user?.userid == testExpectedResult.user?.userid }
            assertTrue { testActualResult.replyto?.id == testExpectedResult.replyto?.id }
            assertTrue { testActualResult.replyto?.kind == testExpectedResult.replyto?.kind }
            assertTrue { testActualResult.replyto?.body == testExpectedResult.replyto?.body }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-8 - Execute Chat Command - Announcement`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputRequest = ExecuteChatCommandRequest(
                command = "This is a test announcement!",
                userid = testCreatedUserData.userid!!,
                eventtype = EventType.ANNOUNCEMENT
            )
            val testExpectedResult = ExecuteChatCommandResponse(
                kind = Kind.CHAT_COMMAND,
                op = EventType.SPEECH,
                room = testCreatedChatRoomData,
                speech = ChatEvent(
                    kind = Kind.CHAT,
                    roomid = testCreatedChatRoomData.id,
                    body = testInputRequest.command,
                    eventtype = EventType.ANNOUNCEMENT,
                    userid = testCreatedUserData.userid,
                    user = testCreatedUserData
                ),
                action = null
            )

            // WHEN
            val testActualResult = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Execute Chat Command - Announcement`() -> testActualResult = \n" +
                        json.encodeToString(
                            ExecuteChatCommandResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.op == testExpectedResult.op }
            assertTrue { testActualResult.speech?.kind == testExpectedResult.speech?.kind }
            assertTrue { testActualResult.speech?.roomid == testExpectedResult.speech?.roomid }
            assertTrue { testActualResult.speech?.body == testExpectedResult.speech?.body }
            assertTrue { testActualResult.speech?.eventtype == testExpectedResult.speech?.eventtype }
            assertTrue { testActualResult.speech?.userid == testExpectedResult.speech?.userid }
            assertTrue { testActualResult.speech?.user?.userid == testExpectedResult.speech?.user?.userid }
            assertTrue { testActualResult.action == testExpectedResult.action }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `U-ERROR-404-User-NOT-found - Execute Chat Command`() = testScope.runTest {
        // GIVEN
        // GIVEN
        val testInputUserId = "non-existing-user-id"
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
            maxreports = testChatRoomData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null
        // WHEN
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputRequest = ExecuteChatCommandRequest(
                command = "Yow error test",
                userid = testInputUserId
            )

            withContext(testDispatcher) {
                chatService.executeChatCommand(
                    chatRoomId = testCreatedChatRoomData.id!!,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-User-NOT-found - Execute Chat Command`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified user was not found within your user database" }
            assertTrue { err.code == 404 }

        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }
    }

    @Test
    fun `U-ERROR-404-REPLY-NOT-FOUND - Execute Chat Command`() = testScope.runTest {
        // GIVEN
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testReplyToIdNonExisting = "non-existing-ID"
            val testInputRequest = ExecuteChatCommandRequest(
                command = "Yow error test",
                userid = testCreatedUserData.userid!!,
                replyto = testReplyToIdNonExisting
            )

            // WHEN
            withContext(testDispatcher) {
                chatService.executeChatCommand(
                    chatRoomId = testCreatedChatRoomData.id!!,
                    request = testInputRequest
                )
            }
        } catch(err: SportsTalkException) {
            println(
                "`ERROR-404-REPLY-NOT-FOUND - Execute Chat Command`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified event was not found" }
            assertTrue { err.code == 404 }

        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `U-ERROR-418-NOT-ALLOWED - Execute Chat Command`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputRequest = ExecuteChatCommandRequest(
                command = "Yow error test",
                userid = testCreatedUserData.userid!!
            )

            // WHEN
            // Execute Chat Command in quick succession
            withContext(testDispatcher) {
                chatService.executeChatCommand(
                    chatRoomId = testCreatedChatRoomData.id!!,
                    request = testInputRequest
                )
            }
            withContext(testDispatcher) {
                chatService.executeChatCommand(
                    chatRoomId = testCreatedChatRoomData.id!!,
                    request = testInputRequest
                )
            }

            advanceUntilIdle()

            fail("Must throttle executeChatCommand() calls in quick succession")
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-418-NOT-ALLOWED - Execute Chat Command`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.message == "418 - Not Allowed. Please wait to send this message again." }
            assertTrue { err.code == 418 }
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `V - List Messages By User`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testInputLimit = 10
            val testExpectedResult = ListMessagesByUser(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            delay(500)
            advanceUntilIdle()
            val testActualResult = chatService.listMessagesByUser(
                chatRoomId = testInputChatRoomId,
                userId = testInputUserId,
                limit = testInputLimit
            )

            // THEN
            println(
                "`List Messages By User`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListMessagesByUser.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.eventtype == testSendMessageData.eventtype
                            && ev.body == testSendMessageData.body
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `W - Bounce User - Ban user`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val bounceMessageBody = "`${testCreatedUserData.handle}` has been banned."
            val testInputRequest = BounceUserRequest(
                userid = testCreatedUserData.userid!!,
                bounce = true,
                announcement = bounceMessageBody
            )
            val testExpectedResult = BounceUserResponse(
                kind = Kind.BOUNCE_USER,
                event = ChatEvent(
                    body = bounceMessageBody,
                    eventtype = EventType.BOUNCE,
                    userid = testCreatedUserData.userid!!
                ),
                room = testCreatedChatRoomData.copy(
                    bouncedusers = listOf(testCreatedUserData.userid!!)
                )
            )

            // WHEN
            val testActualResult = chatService.bounceUser(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Bounce User - Ban user`() -> testActualResult = \n" +
                        json.encodeToString(
                            BounceUserResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.event?.body == testExpectedResult.event?.body }
            assertTrue { testActualResult.event?.eventtype == testExpectedResult.event?.eventtype }
            assertTrue { testActualResult.event?.userid == testExpectedResult.event?.userid }
            assertTrue { testActualResult.room?.bouncedusers == testExpectedResult.room?.bouncedusers }

        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `X - Search Event History`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputUserId = testSendMessageData.userid!!
            val testInputEventTypes = listOf(EventType.SPEECH)
            val testInputRequest = SearchEventHistoryRequest(
                fromuserid = testInputUserId,
                types = testInputEventTypes,
                limit = 10
            )

            // WHEN
            delay(250)
            advanceUntilIdle()
            val testActualResult = chatService.searchEventHistory(
                request = testInputRequest
            )

            // THEN
            println(
                "`Search Event History`() -> testActualResult = \n" +
                        json.encodeToString(
                            SearchEventHistoryResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == Kind.CHAT_LIST }
            assertTrue {
                testActualResult.events.any { _ev ->
                    _ev.id == testSendMessageData.id
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `Y - Update Chat Message`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                enableautoexpiresessions = testChatRoomData.enableautoexpiresessions,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputEventId = testSendMessageData.id!!
            val testInputUserId = "admin"/*testSendMessageData.userid!!*/
            val testInputUpdatedBody = "Updated! Jessy yow!!!"
            val testInputRequest = UpdateChatMessageRequest(
                userid = testInputUserId,
                body = testInputUpdatedBody
            )
            val testExpectedResult = testSendMessageData.copy(
                id = testSendMessageData.id,
                body = testInputUpdatedBody
            )

            // WHEN
            delay(2000)
            advanceUntilIdle()
            val testActualResult = chatService.updateChatMessage(
                chatRoomId = testInputChatRoomId,
                eventId = testInputEventId,
                request = testInputRequest
            )

            // THEN
            println(
                "`Update Chat Message`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.id == testInputEventId }
            assertTrue { testActualResult.body == testInputUpdatedBody }
            assertTrue { testActualResult.roomid == testInputChatRoomId }
            assertTrue { testActualResult.userid == testSendMessageData.userid!! }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `Z - Delete Event`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputEventId = testSendMessageData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testExpectedResult = DeleteEventResponse(
                kind = Kind.DELETED_COMMENT,
                permanentdelete = true,
                event = testSendMessageData.copy(
                    deleted = true
                )
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val testActualResult = chatService.permanentlyDeleteEvent(
                chatRoomId = testInputChatRoomId,
                eventId = testInputEventId,
                userid = testInputUserId
            )

            // THEN
            println(
                "`Delete Event`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteEventResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.permanentdelete == testExpectedResult.permanentdelete }
            assertTrue { testActualResult.event?.id == testExpectedResult.event?.id }
            assertTrue { testActualResult.event?.userid == testExpectedResult.event?.userid }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AA - Flag Message Event as Deleted`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputEventId = testSendMessageData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testInputDeleted = true
            val testInputPermanentIfNoReplies = false
            val testExpectedResult = DeleteEventResponse(
                kind = Kind.DELETED_COMMENT,
                permanentdelete = false,
                event = testSendMessageData.copy(
                    deleted = testInputDeleted
                )
            )

            // WHEN
            delay(1000L)
            advanceUntilIdle()
            val testActualResult = chatService.flagEventLogicallyDeleted(
                chatRoomId = testInputChatRoomId,
                eventId = testInputEventId,
                userid = testInputUserId,
                deleted = testInputDeleted,
                permanentifnoreplies = testInputPermanentIfNoReplies
            )

            // THEN
            println(
                "`Flag Message Event as Deleted`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteEventResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.permanentdelete == testExpectedResult.permanentdelete }
            assertTrue { testActualResult.event?.id == testExpectedResult.event?.id }
            assertTrue { testActualResult.event?.deleted == testExpectedResult.event?.deleted }
            assertTrue { testActualResult.event?.userid == testExpectedResult.event?.userid }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AB - Report a Message`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputRequest = ReportMessageRequest(
                reporttype = "abuse",
                userid = testCreatedUserData.userid!!
            )
            val testExpectedResult = testSendMessageData.copy(
                active = false,
                moderation = "flagged",
                reports = listOf(
                    Report(
                        userid = testInputRequest.userid,
                        reason = testInputRequest.reporttype
                    )
                )
            )

            // WHEN
            delay(1000)
            advanceUntilIdle()
            val testActualResult = chatService.reportMessage(
                chatRoomId = testCreatedChatRoomData.id!!,
                eventId = testSendMessageData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`Report a Message`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.id == testExpectedResult.id }
            /*assertTrue { testActualResult.active == testExpectedResult.active }*/
            /*assertTrue { testActualResult.moderation == testExpectedResult.moderation }*/
            assertTrue { testActualResult.reports == testExpectedResult.reports }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AB-ERROR-404-EVENT-NOT-FOUND - Report a Message`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        val testChatIdNonExisting = "non-existing-ID"
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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputRequest = ReportMessageRequest(
                reporttype = "abuse",
                userid = testCreatedUserData.userid!!
            )

            // WHEN
            withContext(testDispatcher) {
                chatService.reportMessage(
                    chatRoomId = testCreatedChatRoomData.id!!,
                    eventId = testChatIdNonExisting,
                    request = testInputRequest
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404-EVENT-NOT-FOUND - Report a Message`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified event was not found." }
            assertTrue { err.code == 404 }
            assertTrue { err.data?.get("eventId")?.jsonPrimitive?.contentOrNull == testChatIdNonExisting }
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `AC - React to a Message`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputJoinChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputJoinChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "[${Clock.System.now().toEpochMilliseconds()}]Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputRequest = ReactToAMessageRequest(
                userid = testCreatedUserData.userid!!,
                reaction = ReactionType.LIKE,
                reacted = true
            )
            val testExpectedResult = testSendMessageData.copy(
                kind = Kind.CHAT,
                roomid = testCreatedChatRoomData.id,
                eventtype = EventType.SPEECH,
                userid = testInputRequest.userid,
                reactions = listOf(
                    Reaction(
                        type = testInputRequest.reaction,
                        count = 1,
                        users = listOf(
                            User(
                                userid = testCreatedUserData.userid!!,
                                handle = testCreatedUserData.handle!!,
                                displayname = testCreatedUserData.displayname!!
                            )
                        )
                    )
                )
            )

            // WHEN
            delay(1500L)
            advanceUntilIdle()
            val testActualResult = chatService.reactToEvent(
                chatRoomId = testCreatedChatRoomData.id!!,
                eventId = testSendMessageData.id!!,
                request = testInputRequest
            )

            // THEN
            println(
                "`React to a Message`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.eventtype == testExpectedResult.eventtype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            /* Assert each reaction from event response */
            assertTrue {
                testActualResult.reactions.any { rxn ->
                    testExpectedResult.reactions!!.any { expectedRxn ->
                        rxn.type == expectedRxn.type
                                && rxn.count == expectedRxn.count
                                && rxn.users.any { usr -> expectedRxn.users.any { expectedUsr -> usr.userid == expectedUsr.userid } }
                    }
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AD - Shadow Ban User In Room`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testInputApplyEffect = true
            val testInputExpireSeconds = 3_000L

            val testExpectedResult = testCreatedChatRoomData.copy()

            // WHEN
            val testActualResult = chatService.shadowBanUser(
                chatRoomId = testInputChatRoomId,
                userid = testInputUserId,
                applyeffect = testInputApplyEffect,
                expireseconds = testInputExpireSeconds
            )

            // THEN
            println(
                "`Shadow Ban User In Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AD-ERROR-404 - Shadow Ban User In Room - Room Does NOT Exist`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testInputUserId = "NON-Existing-User-ID"
        val testInputApplyEffect = true
        val testInputExpireSeconds = 3_000L

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.shadowBanUser(
                    chatRoomId = testInputRoomId,
                    userid = testInputUserId,
                    applyeffect = testInputApplyEffect,
                    expireseconds = testInputExpireSeconds
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Shadow Ban User In Room - Room Does NOT Exist`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified Room does not exist." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `AD-ERROR-404 - Shadow Ban User In Room - User Does NOT Exist`() = testScope.runTest {
        // GIVEN
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
            maxreports = testChatRoomData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null

        // WHEN
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = "NON-Existing-User-ID"
            val testInputApplyEffect = true
            val testInputExpireSeconds = 3_000L

            withContext(testDispatcher) {
                chatService.shadowBanUser(
                    chatRoomId = testInputRoomId,
                    userid = testInputUserId,
                    applyeffect = testInputApplyEffect,
                    expireseconds = testInputExpireSeconds
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Shadow Ban User In Room - User Does NOT Exist`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified User does not exist." }
            assertTrue { err.code == 404 }

        }
    }

    @Test
    fun `AE - Mute User In Room`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

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
                maxreports = testChatRoomData.maxreports,
                customtags = listOf("tagA", "tagB")
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

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = testCreatedUserData.userid!!
            val testInputApplyEffect = true
            val testInputExpireSeconds = 3_000L

            val testExpectedResult = testCreatedChatRoomData.copy()

            // WHEN
            val testActualResult = chatService.muteUser(
                chatRoomId = testInputChatRoomId,
                userid = testInputUserId,
                applyeffect = testInputApplyEffect,
                expireseconds = testInputExpireSeconds
            )

            // THEN
            println(
                "`Mute User In Room`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatRoom.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.id == testExpectedResult.id }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AE-ERROR-404 - Mute User In Room - Room Does NOT Exist`() = testScope.runTest {
        // GIVEN
        val testInputRoomId = "NON-Existing-Room-ID"
        val testInputUserId = "NON-Existing-User-ID"
        val testInputApplyEffect = true
        val testInputExpireSeconds = 3_000L

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.muteUser(
                    chatRoomId = testInputRoomId,
                    userid = testInputUserId,
                    applyeffect = testInputApplyEffect,
                    expireseconds = testInputExpireSeconds
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Mute User In Room - Room Does NOT Exist`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified Room does not exist." }
            assertTrue { err.code == 404 }

        }

    }

    @Test
    fun `AE-ERROR-404 - Mute User In Room - User Does NOT Exist`() = testScope.runTest {
        // GIVEN
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
            maxreports = testChatRoomData.maxreports,
            customtags = listOf("tagA", "tagB")
        )

        var testCreatedChatRoomData: ChatRoom? = null

        // WHEN
        try {
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputRoomId = testCreatedChatRoomData.id!!
            val testInputUserId = "NON-Existing-User-ID"
            val testInputApplyEffect = true
            val testInputExpireSeconds = 3_000L

            withContext(testDispatcher) {
                chatService.muteUser(
                    chatRoomId = testInputRoomId,
                    userid = testInputUserId,
                    applyeffect = testInputApplyEffect,
                    expireseconds = testInputExpireSeconds
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Mute User In Room - User Does NOT Exist`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified User does not exist." }
            assertTrue { err.code == 404 }

        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
        }

    }

    @Test
    fun `AF-1 - Approve Message - Pre-moderated - Approved`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData = userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
                // Moderation MUST BE SET to "pre"
                .copy(moderation = "pre")
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = ModerationType.pre/*testChatRoomData.moderation*/, // "pre"-moderated
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            assertTrue { testSendMessageData.moderation == ModerationType.pending }

            val testInputRequest = ApproveMessageRequest(
                approve = true
            )
            val testExpectedResult = testSendMessageData.copy(
                moderation = ModerationType.approved
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = chatService.approveMessage(
                eventId = testSendMessageData.id!!,
                approve = testInputRequest.approve
            )

            // THEN
            println(
                "`Approve Message - Pre-moderated - Approved`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.eventtype == testExpectedResult.eventtype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AF-1 - Approve Message - Pre-moderated - Rejected`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null

        try {
            // Should create a test user first
            testCreatedUserData = userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
                // Moderation MUST BE SET to "pre"
                .copy(moderation = "pre")
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = ModerationType.pre/*testChatRoomData.moderation*/, // "pre"-moderated
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            assertTrue { testSendMessageData.moderation == ModerationType.pending }

            val testInputRequest = ApproveMessageRequest(
                approve = false
            )
            val testExpectedResult = testSendMessageData.copy(
                moderation = ModerationType.rejected
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = chatService.approveMessage(
                eventId = testSendMessageData.id!!,
                approve = testInputRequest.approve
            )

            // THEN
            println(
                "`Approve Message - Pre-moderated - Rejected`() -> testActualResult = \n" +
                        json.encodeToString(
                            ChatEvent.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.eventtype == testExpectedResult.eventtype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }
    }

    @Test
    fun `AF-ERROR-404 - Approve Message`() = testScope.runTest {

        // GIVEN
        val testInputNonExistingEventId = "non-existing-event-id"

        // WHEN
        try {
            withContext(testDispatcher) {
                chatService.approveMessage(
                    eventId = testInputNonExistingEventId,
                    approve = true
                )
            }
        } catch (err: SportsTalkException) {
            println(
                "`ERROR-404 - Approve Message`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified event was not found." }
            assertTrue { err.code == 404 }

        }
    }

    @Test
    fun `AF-ERROR-400 - Approve Message - Not in a Moderatable State`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = "${testUserData.handle}_${Random.nextInt(100, 999)}",
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData = userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
                // Moderation MUST BE SET to "pre"
                .copy(moderation = "pre")
            val testCreateChatRoomInputRequest = CreateChatRoomRequest(
                name = testChatRoomData.name!!,
                customid = testChatRoomData.customid,
                description = testChatRoomData.description,
                moderation = ModerationType.post/*testChatRoomData.moderation*/, // "post"-moderated
                enableactions = testChatRoomData.enableactions,
                enableenterandexit = testChatRoomData.enableenterandexit,
                enableprofanityfilter = testChatRoomData.enableprofanityfilter,
                delaymessageseconds = testChatRoomData.delaymessageseconds,
                roomisopen = testChatRoomData.open,
                maxreports = testChatRoomData.maxreports
            )
            // Should create a test chat room first
            testCreatedChatRoomData = chatService.createRoom(testCreateChatRoomInputRequest)

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!,
                handle = testCreatedUserData.handle!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testInputRequest = ApproveMessageRequest(
                approve = true
            )
            val testExpectedResult = testSendMessageData.copy(
                moderation = ModerationType.na
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            chatService.approveMessage(
                eventId = testSendMessageData.id!!,
                approve = testInputRequest.approve
            )

            fail("Must throw error because specified event is NOT in moderatable state.")
        } catch (err: SportsTalkException) {
            // THEN

            println(
                "`A-ERROR-400) Approve Message - Not in a Moderatable State`() -> testActualResult = \n" +
                        json.encodeToString(
                            SportsTalkException.serializer(),
                            err
                        )
            )
            assertTrue { err.kind == Kind.API }
            assertTrue { err.message == "The specified event  is not in a moderatable state." }
            assertTrue { err.code == 400 }

        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    @Test
    fun `AG - List Messages Needing Moderation`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.users.first()
        val testCreateUserInputRequest = CreateUpdateUserRequest(
            userid = RandomString.make(16),
            handle = testUserData.handle,
            displayname = testUserData.displayname,
            pictureurl = testUserData.pictureurl,
            profileurl = testUserData.profileurl
        )

        var testCreatedUserData: User? = null
        var testCreatedChatRoomData: ChatRoom? = null
        try {
            // Should create a test user first
            testCreatedUserData = userService.createOrUpdateUser(request = testCreateUserInputRequest)

            val testChatRoomData = TestData.chatRooms(config.appId).first()
                // Moderation MUST BE SET to "pre"
                .copy(moderation = "pre")
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

            val testInputChatRoomId = testCreatedChatRoomData.id!!
            val testJoinRoomInputRequest = JoinChatRoomRequest(
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should join test created chat room
            delay(300L)
            advanceUntilIdle()
            chatService.joinRoom(
                chatRoomId = testInputChatRoomId,
                request = testJoinRoomInputRequest
            )

            val testInitialSendMessageInputRequest = ExecuteChatCommandRequest(
                command = "Yow Jessy, how are you doin'?",
                userid = testCreatedUserData.userid!!
            )
            // Test Created User Should send a message to the created chat room
            delay(300L)
            advanceUntilIdle()
            val testSendMessageData = chatService.executeChatCommand(
                chatRoomId = testCreatedChatRoomData.id!!,
                request = testInitialSendMessageInputRequest
            ).speech!!

            val testExpectedResult = ListMessagesNeedingModerationResponse(
                kind = Kind.CHAT_LIST,
                events = listOf(testSendMessageData)
            )

            // WHEN
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = chatService.listMessagesNeedingModeration()

            // THEN
            println(
                "`List Messages Needing Moderation`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListMessagesNeedingModerationResponse.serializer(),
                            testActualResult
                        )
            )

            assertTrue { testActualResult.kind == testExpectedResult.kind }
            assertTrue {
                testActualResult.events.any { ev ->
                    ev.id == testSendMessageData.id
                            && ev.userid == testSendMessageData.userid
                            && ev.body == testSendMessageData.body
                            && ev.eventtype == testSendMessageData.eventtype
                }
            }
        } catch (err: SportsTalkException) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            // Perform Delete Test Chat Room
            deleteTestChatRooms(testCreatedChatRoomData?.id)
            // Perform Delete Test User
            deleteTestUsers(testCreatedUserData?.userid)
        }

    }

    object TestData {
        val ADMIN_PASSWORD = "zola"

        internal val USER_HANDLE_RANDOM_NUM = Random(Clock.System.now().toEpochMilliseconds())
        internal val CHATROOM_RANDOM_NUM = Random(Clock.System.now().toEpochMilliseconds())

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
                    customtags = null,
                    customfield1 = null,
                    customfield2 = null,
                    enableactions = true,
                    enableenterandexit = true,
                    open = true,
                    inroom = 1,
                    added = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    whenmodified = null/*DateUtils.toUtcISODateTime(System.currentTimeMillis())*/,
                    moderation = "post",
                    maxreports = 1000L,
                    enableautoexpiresessions = false,
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
                    customtags = null,
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
                    enableautoexpiresessions = false,
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
                    customtags = null,
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
                    enableautoexpiresessions = false,
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
                    customtags = null,
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
                    enableautoexpiresessions = false,
                    enableprofanityfilter = true,
                    delaymessageseconds = 0L
                )
            )
    }

}