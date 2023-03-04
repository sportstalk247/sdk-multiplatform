package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.core.RandomString
import com.sportstalk.sdk.core.ServiceFactory
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.Kind
import com.sportstalk.sdk.model.comment.*
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

class CommentModerationServiceTest {

    private lateinit var config: ClientConfig
    private lateinit var userService: UserService
    private lateinit var commentService: CommentService
    private lateinit var commentModerationService: CommentModerationService
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
        userService = ServiceFactory.User.get(config)
        commentService = ServiceFactory.Comment.get(config)
        commentModerationService = ServiceFactory.CommentModeration.get(config)

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
    fun `A - List Comments In Moderation Queue`() = testScope.runTest {
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

            val testExpectedResult = listOf(
                reportedComment1,
                reportedComment2,
            )

            delay(4000L)
            advanceUntilIdle()
            val testActualResult = commentModerationService.listCommentsInModerationQueue(
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
            deleteTestUsers(CommentServiceTest.TestData.TestUser.userid)
            deleteTestConversations(testConversationData.conversationid)
        }
    }

    @Test
    fun `B - Approve Message In Queue`() = testScope.runTest {
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
            val testActualResult1 = commentModerationService.approveMessageInQueue(
                commentid = reportedComment1.id!!,
                request = ApproveMessageRequest(approve = true),
            )
            val testExpectedResult2 = reportedComment2.copy(
                moderation = "na",
            )
            delay(4000L)
            advanceUntilIdle()
            val testActualResult2 = commentModerationService.approveMessageInQueue(
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
            deleteTestUsers(CommentServiceTest.TestData.TestUser.userid)
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
                        owneruserid = null/*TestUser.userid*/,
                        conversationid = "conversation-id-1",
                        property = "sportstalk247.com/test",
                        moderation = "pre",
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
                        owneruserid = null/*TestUser.userid*/,
                        conversationid = "conversation-id-2",
                        property = "sportstalk247.com/test",
                        moderation = "pre",
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
                        owneruserid = null/*TestUser.userid*/,
                        conversationid = "conversation-id-3",
                        property = "sportstalk247.com/test",
                        moderation = "pre",
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
                        body = "Hello test comment 1-2!!!",
                        originalbody = "Hello test comment 1-2!!!",
                    ),

                    )

    }

}