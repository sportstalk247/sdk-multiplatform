package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.core.RandomString
import com.sportstalk.sdk.core.ServiceFactory
import com.sportstalk.sdk.core.SportsTalk247
import com.sportstalk.sdk.core.api.CommentClient
import com.sportstalk.sdk.core.api.UserClient
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.Kind
import com.sportstalk.sdk.model.chat.ModerationType
import com.sportstalk.sdk.model.comment.*
import com.sportstalk.sdk.model.reactions.Reaction
import com.sportstalk.sdk.model.reactions.ReactionType
import com.sportstalk.sdk.model.reports.Report
import com.sportstalk.sdk.model.reports.ReportType
import com.sportstalk.sdk.model.user.CreateUpdateUserRequest
import com.sportstalk.sdk.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import kotlin.random.Random
import kotlin.test.*

class CommentClientTest {
    private lateinit var config: ClientConfig
    private lateinit var userService: UserClient
    private lateinit var commentService: CommentClient
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
        commentService = SportsTalk247.CommentClient(config)

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
     * Helper function to clean up Test Conversations from the Backend Server
     */
    private suspend fun deleteTestConversations(vararg conversationIds: String?) {
        for (id in conversationIds) {
            id ?: continue
            try {
                commentService.deleteConversation(id)
            } catch (err: Throwable) {
            }
        }
    }

    @Test
    fun `A - Create Conversation`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.conversations(config.appId)[0]
        val testInputRequest = CreateOrUpdateConversationRequest(
            conversationid = testExpectedData.conversationid!!,
            property = testExpectedData.property!!,
            moderation = testExpectedData.moderation!!,
            enableprofanityfilter = testExpectedData.enableprofanityfilter,
            title = testExpectedData.title,
            open = testExpectedData.open,
            customid = testExpectedData.customid
        )

        val testExpectedResult = testExpectedData

        // WHEN
        try {
            val testActualResult = commentService.createOrUpdateConversation(testInputRequest)

            // THEN
            println(
                "`Create Conversation`() -> testActualResult = \n" +
                        json.encodeToString(
                            Conversation.serializer(),
                            testActualResult,
                        )
            )


            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.property == testExpectedResult.property }
            assertTrue { testActualResult.moderation == testExpectedResult.moderation }
            assertTrue { testActualResult.enableprofanityfilter == testExpectedResult.enableprofanityfilter }
            assertTrue { testActualResult.title == testExpectedResult.title }
            assertTrue { testActualResult.customid == testExpectedResult.customid }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestConversations(testExpectedData.conversationid)
        }
    }

    @Test
    fun `B - Get Conversation`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.conversations(config.appId)[0]
        val testInputRequest = CreateOrUpdateConversationRequest(
            conversationid = testExpectedData.conversationid!!,
            property = testExpectedData.property!!,
            moderation = testExpectedData.moderation!!,
            enableprofanityfilter = testExpectedData.enableprofanityfilter,
            title = testExpectedData.title,
            open = testExpectedData.open,
            customid = testExpectedData.customid
        )

        // First create the Conversation instance

        // WHEN
        try {
            val createdConversation = commentService.createOrUpdateConversation(testInputRequest)
            val testExpectedResult = createdConversation

            delay(300L)
            advanceUntilIdle()
            val testActualResult =
                commentService.getConversation(conversationid = createdConversation.conversationid!!)

            // THEN
            println(
                "`Get Conversation`() -> testActualResult = \n" +
                        json.encodeToString(
                            Conversation.serializer(),
                            testActualResult,
                        )
            )


            assertTrue { testActualResult == testExpectedResult }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestConversations(testExpectedData.conversationid)
        }
    }

    @Test
    fun `C - Get Conversation By Custom ID`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.conversations(config.appId)[0]
        val testInputRequest = CreateOrUpdateConversationRequest(
            conversationid = testExpectedData.conversationid!!,
            property = testExpectedData.property!!,
            moderation = testExpectedData.moderation!!,
            enableprofanityfilter = testExpectedData.enableprofanityfilter,
            title = testExpectedData.title,
            open = testExpectedData.open,
            customid = testExpectedData.customid
        )

        // WHEN
        try {
            val createdConversation = commentService.createOrUpdateConversation(testInputRequest)
            val testExpectedResult = createdConversation

            delay(300L)
            advanceUntilIdle()
            val testActualResult =
                commentService.getConversationByCustomId(customid = createdConversation.customid!!)

            // THEN
            println(
                "`Get Conversation By Custom ID`() -> testActualResult = \n" +
                        json.encodeToString(
                            Conversation.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult == testExpectedResult }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestConversations(testExpectedData.conversationid)
        }
    }

    @Test
    fun `D - List Conversations`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.conversations(config.appId)
        val testInputRequests = testExpectedData.map { item ->
            CreateOrUpdateConversationRequest(
                conversationid = item.conversationid!!,
                property = item.property!!,
                moderation = item.moderation!!,
                enableprofanityfilter = item.enableprofanityfilter,
                title = item.title,
                open = item.open,
                customid = item.customid
            )
        }

        // WHEN
        val createdConversations = mutableListOf<Conversation>()
        try {
            // Create the Conversation instances
            for (input in testInputRequests) {
                createdConversations.add(
                    commentService.createOrUpdateConversation(input)
                )
            }

            val testExpectedResult = createdConversations

            delay(300L)
            advanceUntilIdle()
            val testActualResult = commentService.listConversations()

            // THEN
            println(
                "`List Conversations`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListConversations.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversations.containsAll(testExpectedResult) }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestConversations(*(testExpectedData.map { it.conversationid }.toTypedArray()))
        }
    }

    @Test
    fun `E - Batch Get Conversation Details`() = testScope.runTest {
        // GIVEN
        val testExpectedData = TestData.conversations(config.appId)
        val testInputRequests = testExpectedData.map { item ->
            CreateOrUpdateConversationRequest(
                conversationid = item.conversationid!!,
                property = item.property!!,
                moderation = item.moderation!!,
                enableprofanityfilter = item.enableprofanityfilter,
                title = item.title,
                open = item.open,
                customid = item.customid
            )
        }

        // WHEN
        val createdConversations = mutableListOf<Conversation>()
        try {
            // Create the Conversation instances
            for (input in testInputRequests) {
                createdConversations.add(
                    commentService.createOrUpdateConversation(input)
                )
            }

            val testExpectedResult = createdConversations

            delay(1500L)
            advanceUntilIdle()
            val testActualResult = commentService.batchGetConversationDetails(
                ids = createdConversations.mapNotNull { it.conversationid },
            )

            // THEN
            println(
                "`Batch Get Conversation Details`() -> testActualResult = \n" +
                        json.encodeToString(
                            BatchGetConversationDetailsResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.itemcount == testExpectedResult.size.toLong() }
            assertTrue {
                testActualResult.conversations.mapNotNull { it.conversationid }
                    .containsAll(testExpectedResult.mapNotNull { it.conversationid })
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestConversations(*(testExpectedData.map { it.conversationid }.toTypedArray()))
        }
    }

    @Test
    fun `F - React to Conversation Topic`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser

        val testConversationData = TestData.conversations(config.appId)[0]
        val testInputRequest = CreateOrUpdateConversationRequest(
            conversationid = testConversationData.conversationid!!,
            property = testConversationData.property!!,
            moderation = testConversationData.moderation!!,
            enableprofanityfilter = testConversationData.enableprofanityfilter,
            title = testConversationData.title,
            open = testConversationData.open,
            customid = testConversationData.customid
        )

        // WHEN
        try {
            // First create User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )

            // Then, create Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(testInputRequest)

            delay(1000L)
            advanceUntilIdle()
            val testActualResult = commentService.reactToConversationTopic(
                conversationid = createdConversation.conversationid!!,
                request = ReactToConversationTopicRequest(
                    userid = createdUser.userid!!,
                    reaction = ReactionType.LIKE,
                    reacted = true,
                )
            )

            // THEN
            println(
                "`React to Conversation Topic`() -> testActualResult = \n" +
                        json.encodeToString(
                            Conversation.serializer(),
                            testActualResult,
                        )
            )


            assertTrue { testActualResult.reactions?.isNotEmpty() == true }
            assertTrue { testActualResult.reactions?.any { rxn -> rxn.type == ReactionType.LIKE } == true }
            assertTrue { testActualResult.reactions?.any { rxn -> rxn.count == 1L } == true }
            assertTrue { testActualResult.reactions?.any { rxn -> rxn.users.any { usr -> usr.userid == createdUser.userid } } == true }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(testUserData.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `G - Create Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Finally, create Comment instance
            delay(1500L)
            advanceUntilIdle()
            val testExpectedResult = testCommentData
            val testActualResult = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // THEN
            println(
                "`Create Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue {
                testActualResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `H-1 - Reply To Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]
        val testCommentReplyData = TestData.comments(config.appId)[1]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(1500L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            val testExpectedResult = testCommentReplyData.copy(
                parentid = createdComment.id,
                conversationid = createdComment.conversationid,
            )
            delay(1500L)
            advanceUntilIdle()
            val testActualResult = commentService.replyToComment(
                conversationid = createdConversation.conversationid!!,
                replyto = createdComment.id!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentReplyData.body!!,
                    customtype = testCommentReplyData.customtype,
                    customfield1 = testCommentReplyData.customfield1,
                    customfield2 = testCommentReplyData.customfield2,
                    custompayload = testCommentReplyData.custompayload,
                )
            )

            // THEN
            println(
                "`Reply To Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.parentid == testExpectedResult.parentid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue {
                testActualResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `H-2 - Reply To Comment - Get Reply Count`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]
        val testCommentReplyData = TestData.comments(config.appId)[1]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(1500L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Attempt Reply to a Comment
            commentService.replyToComment(
                conversationid = createdConversation.conversationid!!,
                replyto = createdComment.id!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentReplyData.body!!,
                    customtype = testCommentReplyData.customtype,
                    customfield1 = testCommentReplyData.customfield1,
                    customfield2 = testCommentReplyData.customfield2,
                    custompayload = testCommentReplyData.custompayload,
                )
            )

            val testExpectedResult = createdConversation.copy(
                replycount = 1,
            )
            delay(1500L)
            advanceUntilIdle()
            val testActualResult = commentService.getConversation(
                conversationid = createdConversation.conversationid!!,
            )

            // Wait atleast 3 seconds to get updated Conversation
            delay(3000L)
            advanceUntilIdle()

            // THEN
            println(
                "`Reply To Comment - Get Reply Count`() -> testActualResult = \n" +
                        json.encodeToString(
                            Conversation.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.replycount == 1L }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `I - List Replies`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]
        val testCommentReplyData1 = TestData.comments(config.appId)[1]
        val testCommentReplyData2 = TestData.comments(config.appId)[2]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Create several reply comments
            val replyComments = mutableListOf<Comment>().apply {
                add(
                    commentService.replyToComment(
                        conversationid = createdConversation.conversationid!!,
                        replyto = createdComment.id!!,
                        request = CreateCommentRequest(
                            userid = createdUser.userid!!,
                            displayname = createdUser.displayname,
                            body = testCommentReplyData1.body!!,
                            customtype = testCommentReplyData1.customtype,
                            customfield1 = testCommentReplyData1.customfield1,
                            customfield2 = testCommentReplyData1.customfield2,
                            custompayload = testCommentReplyData1.custompayload,
                        )
                    )
                )
                add(
                    commentService.replyToComment(
                        conversationid = createdConversation.conversationid!!,
                        replyto = createdComment.id!!,
                        request = CreateCommentRequest(
                            userid = createdUser.userid!!,
                            displayname = createdUser.displayname,
                            body = testCommentReplyData2.body!!,
                            customtype = testCommentReplyData2.customtype,
                            customfield1 = testCommentReplyData2.customfield1,
                            customfield2 = testCommentReplyData2.customfield2,
                            custompayload = testCommentReplyData2.custompayload,
                        )
                    )
                )
            }

            val testExpectedResult = replyComments
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.listReplies(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                includechildren = true,
                includeinactive = true
            )

            // THEN
            println(
                "`List Replies`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListComments.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.itemcount == testExpectedResult.size }
            assertTrue { testActualResult.conversation?.conversationid == createdConversation?.conversationid }
            assertTrue {
                testActualResult.comments.any { comment ->
                    replyComments.any { replyComment -> replyComment.id == comment.id }
                            && replyComments.any { replyComment -> replyComment.parentid == comment.parentid }
                            && replyComments.any { replyComment -> replyComment.userid == comment.userid }
                            && replyComments.any { replyComment -> replyComment.body == comment.body }
                            && comment.hierarchy?.isNotEmpty() == true
                }
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `J - Get Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create a Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            delay(2500L)
            advanceUntilIdle()
            val testExpectedResult = createdComment
            val testActualResult = commentService.getComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
            )

            // THEN
            println(
                "`Get Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue {
                testActualResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customtype?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield1?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.customfield2?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }
            assertTrue {
                testActualResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() } == testExpectedResult.custompayload?.trim()
                    ?.takeIf { it.isNotEmpty() }
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `K - List Comments`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData1 = TestData.comments(config.appId)[0]
        val testCommentData2 = TestData.comments(config.appId)[1]
        val testCommentData3 = TestData.comments(config.appId)[2]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )

            // Create multiple comment instances
            delay(300L)
            advanceUntilIdle()
            val createdComments = mutableListOf<Comment>().apply {
                add(
                    commentService.createComment(
                        conversationid = createdConversation.conversationid!!,
                        request = CreateCommentRequest(
                            userid = createdUser.userid!!,
                            displayname = createdUser.displayname,
                            body = testCommentData1.body!!,
                            customtype = testCommentData1.customtype,
                            customfield1 = testCommentData1.customfield1,
                            customfield2 = testCommentData1.customfield2,
                            custompayload = testCommentData1.custompayload,
                        )
                    )
                )
                add(
                    commentService.createComment(
                        conversationid = createdConversation.conversationid!!,
                        request = CreateCommentRequest(
                            userid = createdUser.userid!!,
                            displayname = createdUser.displayname,
                            body = testCommentData2.body!!,
                            customtype = testCommentData2.customtype,
                            customfield1 = testCommentData2.customfield1,
                            customfield2 = testCommentData2.customfield2,
                            custompayload = testCommentData2.custompayload,
                        )
                    )
                )
                add(
                    commentService.createComment(
                        conversationid = createdConversation.conversationid!!,
                        request = CreateCommentRequest(
                            userid = createdUser.userid!!,
                            displayname = createdUser.displayname,
                            body = testCommentData3.body!!,
                            customtype = testCommentData3.customtype,
                            customfield1 = testCommentData3.customfield1,
                            customfield2 = testCommentData3.customfield2,
                            custompayload = testCommentData3.custompayload,
                        )
                    )
                )
            }

            val testExpectedResult = createdComments
            delay(3000L)
            advanceUntilIdle()
            val testActualResult = commentService.listComments(
                conversationid = createdConversation.conversationid!!,
                cursor = null,
                limit = null,
                includechildren = true,
                includeinactive = true
            )

            // THEN
            println(
                "`List Comments`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListComments.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.itemcount == testExpectedResult.size }
            assertTrue { testActualResult.conversation?.conversationid == createdConversation.conversationid }
            assertTrue {
                testActualResult.comments.any { comment ->
                    createdComments.any { createdComment -> createdComment.id == comment.id }
                            && createdComments.any { createdComment -> createdComment.conversationid == comment.conversationid }
                            && createdComments.any { createdComment -> createdComment.userid == comment.userid }
                            && createdComments.any { createdComment -> createdComment.body == comment.body }
                            && (comment.hierarchy == null || comment.hierarchy?.isEmpty() == true)
                }
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `L - List Replies Batch`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData1 = TestData.comments(config.appId)[0]
        val testCommentData2 = TestData.comments(config.appId)[1]
        val testCommentData3 = TestData.comments(config.appId)[2]
        val testCommentData4 = TestData.comments(config.appId)[3]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )

            // Create multiple comment instances with 1 reply each
            delay(300L)
            advanceUntilIdle()
            val parentComment1 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData1.body!!,
                    customtype = testCommentData1.customtype,
                    customfield1 = testCommentData1.customfield1,
                    customfield2 = testCommentData1.customfield2,
                    custompayload = testCommentData1.custompayload,
                )
            )
            delay(2000L)
            advanceUntilIdle()
            val replyComment1 = commentService.replyToComment(
                conversationid = createdConversation.conversationid!!,
                replyto = parentComment1.id!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData2.body!!,
                    customtype = testCommentData2.customtype,
                    customfield1 = testCommentData2.customfield1,
                    customfield2 = testCommentData2.customfield2,
                    custompayload = testCommentData2.custompayload,
                )
            )
            val parentComment2 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData3.body!!,
                    customtype = testCommentData3.customtype,
                    customfield1 = testCommentData3.customfield1,
                    customfield2 = testCommentData3.customfield2,
                    custompayload = testCommentData3.custompayload,
                )
            )
            delay(2000L)
            advanceUntilIdle()
            val replyComment2 = commentService.replyToComment(
                conversationid = createdConversation.conversationid!!,
                replyto = parentComment2.id!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData4.body!!,
                    customtype = testCommentData4.customtype,
                    customfield1 = testCommentData4.customfield1,
                    customfield2 = testCommentData4.customfield2,
                    custompayload = testCommentData4.custompayload,
                )
            )

            val testExpectedResult = ListRepliesBatchResponse(
                kind = Kind.COMMENT_REPLIES_BY_PARENT,
                repliesgroupedbyparentid = listOf(
                    ListRepliesBatchResponse.CommentReplyGroup(
                        kind = Kind.COMMENT_REPLY_GROUP,
                        parentid = parentComment1.id,
                        comments = listOf(replyComment1)
                    ),
                    ListRepliesBatchResponse.CommentReplyGroup(
                        kind = Kind.COMMENT_REPLY_GROUP,
                        parentid = parentComment2.id,
                        comments = listOf(replyComment2)
                    ),
                )
            )

            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.listRepliesBatch(
                conversationid = createdConversation.conversationid!!,
                childlimit = 50,
                parentids = listOf(parentComment1.id!!, parentComment2.id!!),
                includeinactive = true,
            )

            // THEN
            println(
                "`List Replies Batch`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListRepliesBatchResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.repliesgroupedbyparentid.size == testExpectedResult.repliesgroupedbyparentid.size }
            assertTrue { testActualResult.repliesgroupedbyparentid[0].parentid == testExpectedResult.repliesgroupedbyparentid[0].parentid }
            assertTrue { testActualResult.repliesgroupedbyparentid[0].comments.size == testExpectedResult.repliesgroupedbyparentid[0].comments.size }
            assertTrue { testActualResult.repliesgroupedbyparentid[0].comments.first().id == testExpectedResult.repliesgroupedbyparentid[0].comments.first().id }
            assertTrue { testActualResult.repliesgroupedbyparentid[0].comments.first().body == testExpectedResult.repliesgroupedbyparentid[0].comments.first().body }
            assertTrue { testActualResult.repliesgroupedbyparentid[0].comments.first().parentid != null }
            assertTrue { testExpectedResult.repliesgroupedbyparentid[0].comments.first().parentid != null }
            assertTrue { testActualResult.repliesgroupedbyparentid[1].parentid == testExpectedResult.repliesgroupedbyparentid[1].parentid }
            assertTrue { testActualResult.repliesgroupedbyparentid[1].comments.size == testExpectedResult.repliesgroupedbyparentid[1].comments.size }
            assertTrue { testActualResult.repliesgroupedbyparentid[1].comments.first().id == testExpectedResult.repliesgroupedbyparentid[1].comments.first().id }
            assertTrue { testActualResult.repliesgroupedbyparentid[1].comments.first().body == testExpectedResult.repliesgroupedbyparentid[1].comments.first().body }
            assertTrue { testActualResult.repliesgroupedbyparentid[1].comments.first().parentid != null }
            assertTrue { testExpectedResult.repliesgroupedbyparentid[1].comments.first().parentid != null }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `M - React To Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, react to above comment
            val reaction = Reaction(
                type = ReactionType.LIKE,
                count = 1,
                users = listOf(createdUser),
            )
            val testExpectedResult = createdComment.copy(
                reactions = listOf(reaction)
            )
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.reactToComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                request = ReactToCommentRequest(
                    userid = createdUser.userid!!,
                    reaction = ReactionType.LIKE,
                    reacted = true,
                )
            )

            // THEN
            println(
                "`React To Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.reactions?.any { rxn -> rxn.type == reaction.type!! } == true }
            assertTrue { testActualResult.reactions?.any { rxn -> rxn.count == 1L } == true }
            assertTrue {
                testActualResult.reactions?.any { rxn ->
                    rxn.users.any { usr ->
                        reaction.users.any { it.userid == usr.userid }
                    }
                } == true
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `N - Vote on Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, vote on above comment
            val vote = VoteDetail(
                type = VoteType.Up,
                count = 1L,
                users = listOf(createdUser),
            )
            val testExpectedResult = createdComment.copy(
                votes = listOf(vote),
                votecount = 1,
                votescore = 1,
            )
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.voteOnComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                request = VoteOnCommentRequest(
                    vote = VoteType.Up,
                    userid = createdUser.userid!!,
                ),
            )

            // THEN
            println(
                "`Vote on Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.votecount == 1L }
            assertTrue { testActualResult.votescore == 1L }
            assertTrue { testActualResult.votes!!.first().count == 1L }
            assertTrue { testActualResult.votes!!.first().type == VoteType.Up }
            assertTrue {
                testActualResult.votes!!.first().users?.any { usr ->
                    usr.userid == createdUser.userid
                } == true
            }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `O - Report Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(200)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, report above comment
            val report = Report(
                userid = createdUser.userid!!,
                reason = ReportType.ABUSE,
            )
            val testExpectedResult = createdComment.copy(
                reports = listOf(report)
            )
            delay(2000)
            advanceUntilIdle()
            val testActualResult = commentService.reportComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                request = ReportCommentRequest(
                    userid = createdUser.userid!!,
                    reporttype = ReportType.ABUSE,
                ),
            )

            // THEN
            println(
                "`Report Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }
            assertTrue { testActualResult.reports == testExpectedResult.reports }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `P - Update Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]
        val testCommentUpdateData = TestData.comments(config.appId)[1]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, update above comment
            val testExpectedResult = createdComment.copy(
                body = testCommentUpdateData.body,

                )
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.updateComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                request = UpdateCommentRequest(
                    userid = createdUser.userid!!,
                    body = testCommentUpdateData.body!!,
                ),
            )

            // THEN
            println(
                "`Update Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == testExpectedResult.conversationid }
            assertTrue { testActualResult.commenttype == testExpectedResult.commenttype }
            assertTrue { testActualResult.userid == testExpectedResult.userid }
            assertTrue { testActualResult.body == testExpectedResult.body }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `Q - Flag Comment Logically Deleted`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, flag comment as logically deleted
            val testExpectedResult = createdComment.copy(
                body = "(comment deleted)",
                deleted = true,
            )
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.flagCommentLogicallyDeleted(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                userid = createdUser.userid!!,
                deleted = true,
            )

            // THEN
            println(
                "`Flag Comment Logically Deleted`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteCommentResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.permanentdelete == false }
            assertTrue { testActualResult.comment?.id == testExpectedResult.id }
            assertTrue { testActualResult.comment?.userid == testExpectedResult.userid }
            assertTrue { testActualResult.comment?.body == testExpectedResult.body }   // Comment now has body set to "(comment deleted)"
            assertTrue { testActualResult.comment?.deleted == testExpectedResult.deleted }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `Q-1 - Flag Comment Logically Deleted - permanintifnoreplies`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, flag comment as logically deleted
            val testExpectedResult = createdComment.copy(
                body = "(comment deleted)",
                deleted = true,
            )
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.flagCommentLogicallyDeleted(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
                userid = createdUser.userid!!,
                deleted = true,
                permanentifnoreplies = true,
            )

            // THEN
            println(
                "`Flag Comment Logically Deleted - permanentifnoreplies`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteCommentResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.permanentdelete == true }
            assertTrue { testActualResult.comment?.userid == testExpectedResult.userid }
            assertTrue { testActualResult.comment?.body == testExpectedResult.body }   // Comment now has body set to "(comment deleted)"
            assertTrue { testActualResult.comment?.deleted == testExpectedResult.deleted }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `R - Delete Comment`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, delete above comment
            delay(2000L)
            advanceUntilIdle()
            val testExpectedResult = createdComment
            val testActualResult = commentService.permanentlyDeleteComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment.id!!,
            )

            // THEN
            println(
                "`Delete Comment`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteCommentResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.permanentdelete == true }
            assertTrue { testActualResult.comment?.id == testExpectedResult.id }
            assertTrue { testActualResult.comment?.userid == testExpectedResult.userid }
            assertTrue { testActualResult.comment?.body == testExpectedResult.body }   // Comment now has body set to "(comment deleted)"
            assertTrue { testActualResult.comment?.deleted == testExpectedResult.deleted }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `S - Delete Conversation`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an initial Comment instance
            delay(300L)
            advanceUntilIdle()
            val createdComment = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Finally, delete conversation
            delay(2000L)
            advanceUntilIdle()
            val testActualResult = commentService.deleteConversation(
                conversationid = createdConversation.conversationid!!,
            )

            // THEN
            println(
                "`Delete Conversation`() -> testActualResult = \n" +
                        json.encodeToString(
                            DeleteConversationResponse.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.conversationid == createdConversation.conversationid }
            assertTrue { testActualResult.deletedconversations == 1L }
            assertTrue { testActualResult.deletedcomments == 1L }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(TestData.TestUser.userid)
        }
    }

    @Test
    fun `T - List Comments In Moderation Queue`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = ModerationType.pre,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an 2 Comment instances
            delay(300L)
            advanceUntilIdle()
            val createdComment1 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )
            delay(300L)
            advanceUntilIdle()
            val createdComment2 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Report above comments
            delay(2000L)
            advanceUntilIdle()
            val reportedComment1 = commentService.reportComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment1.id!!,
                request = ReportCommentRequest(
                    userid = createdUser.userid!!,
                    reporttype = ReportType.ABUSE,
                ),
            )
            delay(2000L)
            advanceUntilIdle()
            val reportedComment2 = commentService.reportComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment2.id!!,
                request = ReportCommentRequest(
                    userid = createdUser.userid!!,
                    reporttype = ReportType.SPAM,
                ),
            )

            val testExpectedResult = listOf(
                reportedComment1,
                reportedComment2,
            )

            delay(4000L)
            advanceUntilIdle()
            val testActualResult = commentService.listCommentsInModerationQueue(
                conversationid = createdConversation.conversationid!!,
            )

            // THEN
            println(
                "`List Comments In Moderation Queue`() -> testActualResult = \n" +
                        json.encodeToString(
                            ListComments.serializer(),
                            testActualResult,
                        )
            )

            assertTrue { testActualResult.itemcount == 2 }
            assertTrue { testActualResult.comments.containsAll(testExpectedResult) }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(CommentClientTest.TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `U - Approve Message In Queue`() = testScope.runTest {
        // GIVEN
        val testUserData = TestData.TestUser
        val testConversationData = TestData.conversations(config.appId)[0]
        val testCommentData = TestData.comments(config.appId)[0]

        // WHEN
        try {
            // First create the User instance
            val createdUser = userService.createOrUpdateUser(
                request = CreateUpdateUserRequest(
                    userid = testUserData.userid!!,
                    handle = testUserData.handle,
                    displayname = testUserData.displayname,
                    pictureurl = testUserData.pictureurl,
                    profileurl = testUserData.profileurl,
                )
            )
            // Then, create the Conversation instance
            val createdConversation = commentService.createOrUpdateConversation(
                request = CreateOrUpdateConversationRequest(
                    conversationid = testConversationData.conversationid!!,
                    property = testConversationData.property!!,
                    moderation = testConversationData.moderation!!,
                    enableprofanityfilter = testConversationData.enableprofanityfilter,
                    title = testConversationData.title,
                    open = testConversationData.open,
                    customid = testConversationData.customid
                )
            )
            // Create an 2 Comment instances
            delay(300L)
            advanceUntilIdle()
            val createdComment1 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )
            delay(300L)
            advanceUntilIdle()
            val createdComment2 = commentService.createComment(
                conversationid = createdConversation.conversationid!!,
                request = CreateCommentRequest(
                    userid = createdUser.userid!!,
                    displayname = createdUser.displayname,
                    body = testCommentData.body!!,
                    customtype = testCommentData.customtype,
                    customfield1 = testCommentData.customfield1,
                    customfield2 = testCommentData.customfield2,
                    custompayload = testCommentData.custompayload,
                )
            )

            // Report above comments
            delay(2000L)
            advanceUntilIdle()
            val reportedComment1 = commentService.reportComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment1.id!!,
                request = ReportCommentRequest(
                    userid = createdUser.userid!!,
                    reporttype = ReportType.ABUSE,
                ),
            )
            delay(2000L)
            advanceUntilIdle()
            val reportedComment2 = commentService.reportComment(
                conversationid = createdConversation.conversationid!!,
                commentid = createdComment2.id!!,
                request = ReportCommentRequest(
                    userid = createdUser.userid!!,
                    reporttype = ReportType.SPAM,
                ),
            )

            // Finally, Approve/Deny for-moderation comments
            val testExpectedResult1 = reportedComment1.copy(
                moderation = "na",
            )
            delay(4000L)
            advanceUntilIdle()
            val testActualResult1 = commentService.approveMessageInQueue(
                commentid = reportedComment1.id!!,
                request = ApproveMessageRequest(approve = true),
            )
            val testExpectedResult2 = reportedComment2.copy(
                moderation = "na",
            )
            delay(4000L)
            advanceUntilIdle()
            val testActualResult2 = commentService.approveMessageInQueue(
                commentid = reportedComment2.id!!,
                request = ApproveMessageRequest(approve = true),
            )

            // THEN
            println(
                "`Approve Message In Queue`() -> testActualResult1 = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult1,
                        )
            )
            println(
                "`Approve Message In Queue`() -> testActualResult2 = \n" +
                        json.encodeToString(
                            Comment.serializer(),
                            testActualResult2,
                        )
            )

            assertTrue { testActualResult1.id == testActualResult1.id }
            assertTrue { testActualResult1.body == testActualResult1.body }
            assertTrue { testActualResult1.moderation == testActualResult1.moderation }
            assertTrue { testActualResult2.id == testActualResult2.id }
            assertTrue { testActualResult2.body == testActualResult2.body }
            assertTrue { testActualResult2.moderation == testActualResult2.moderation }

        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            deleteTestUsers(CommentClientTest.TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    object TestData {

        private val USER_HANDLE_RANDOM_NUM = Random(Clock.System.now().toEpochMilliseconds())

        val TestUser = User(
            kind = Kind.USER,
            userid = RandomString.make(16),
            handle = "handle_test1_${USER_HANDLE_RANDOM_NUM.nextInt(99)}",
            displayname = "Test 1",
            pictureurl = "http://www.thepresidentshalloffame.com/media/reviews/photos/original/a9/c7/a6/44-1-george-washington-18-1549729902.jpg",
            profileurl = "http://www.thepresidentshalloffame.com/1-george-washington"
        )

        private val _conversations: List<Conversation>? = null
        fun conversations(appId: String): List<Conversation> =
            _conversations
                ?: listOf(
                    Conversation(
                        kind = Kind.CONVERSATION,
                        appid = appId,
                        owneruserid = null,/*TestUser.userid*/
                        conversationid = "conversation-id-1",
                        property = "sportstalk247.com/test",
                        moderation = "post",
                        maxreports = null,
                        enableprofanityfilter = true,
                        title = "Test Conversation 1",
                        maxcommentlen = null,
                        commentcount = null,
                        reactions = emptyList(),
                        likecount = null,
                        open = true,
                        added = null,
                        whenmodified = null,
                        customtype = "",
                        customid = "conversation-customid-1",
                        customtags = listOf("taga", "tagb"),
                        custompayload = "{ num: 0 }",
                        customfield1 = null,
                        customfield2 = null,
                    ),
                    Conversation(
                        kind = Kind.CONVERSATION,
                        appid = appId,
                        owneruserid = null,/*TestUser.userid*/
                        conversationid = "conversation-id-2",
                        property = "sportstalk247.com/test",
                        moderation = "post",
                        maxreports = null,
                        enableprofanityfilter = true,
                        title = "Test Conversation 2",
                        maxcommentlen = null,
                        commentcount = null,
                        reactions = emptyList(),
                        likecount = null,
                        open = true,
                        added = null,
                        whenmodified = null,
                        customtype = "",
                        customid = "conversation-customid-2",
                        customtags = listOf("tagc", "tagd"),
                        custompayload = "{ num: 1 }",
                        customfield1 = null,
                        customfield2 = null,
                    ),
                    Conversation(
                        kind = Kind.CONVERSATION,
                        appid = appId,
                        owneruserid = null,/*TestUser.userid*/
                        conversationid = "conversation-id-3",
                        property = "sportstalk247.com/test",
                        moderation = "post",
                        maxreports = null,
                        enableprofanityfilter = true,
                        title = "Test Conversation 3",
                        maxcommentlen = null,
                        commentcount = null,
                        reactions = emptyList(),
                        likecount = null,
                        open = true,
                        added = null,
                        whenmodified = null,
                        customtype = "",
                        customid = "conversation-customid-3",
                        customtags = listOf("tage", "tagf"),
                        custompayload = "{ num: 2 }",
                        customfield1 = null,
                        customfield2 = null,
                    ),
                )

        private val _comments: List<Comment>? = null
        fun comments(appId: String): List<Comment> =
            _comments
                ?: listOf(
                    Comment(
                        kind = Kind.COMMENT,
                        id = "comment-id-1-0",
                        appid = appId,
                        conversationid = conversations(appId)[0].conversationid,
                        commenttype = CommentType.COMMENT,
                        added = null,
                        modified = null,
                        tsunix = null,
                        userid = TestUser.userid,
                        user = TestUser,
                        body = "Hello test comment 1-0!!!",
                        originalbody = "Hello test comment 1-0!!!",
                    ),
                    Comment(
                        kind = Kind.COMMENT,
                        id = "comment-id-1-1",
                        appid = appId,
                        conversationid = conversations(appId)[0].conversationid,
                        commenttype = CommentType.COMMENT,
                        added = null,
                        modified = null,
                        tsunix = null,
                        userid = TestUser.userid,
                        user = TestUser,
                        body = "Hello test comment 1-1!!!",
                        originalbody = "Hello test comment 1-1!!!",
                    ),
                    Comment(
                        kind = Kind.COMMENT,
                        id = "comment-id-2-0",
                        appid = appId,
                        conversationid = conversations(appId)[0].conversationid,
                        commenttype = CommentType.COMMENT,
                        added = null,
                        modified = null,
                        tsunix = null,
                        userid = TestUser.userid,
                        user = TestUser,
                        body = "Hello test comment 2-0!!!",
                        originalbody = "Hello test comment 2-0!!!",
                    ),
                    Comment(
                        kind = Kind.COMMENT,
                        id = "comment-id-2-1",
                        appid = appId,
                        conversationid = conversations(appId)[0].conversationid,
                        commenttype = CommentType.COMMENT,
                        added = null,
                        modified = null,
                        tsunix = null,
                        userid = TestUser.userid,
                        user = TestUser,
                        body = "Hello test comment 2-1!!!",
                        originalbody = "Hello test comment 2-1!!!",
                    ),

                    )

    }

}