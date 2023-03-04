package com.sportstalk.sdk.core.impl

import com.sportstalk.sdk.core.ConfigUtils
import com.sportstalk.sdk.core.ServiceFactory
import com.sportstalk.sdk.core.api.CommentClient
import com.sportstalk.sdk.model.ApiResponse
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.comment.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CommentClientImpl
internal constructor(
    private val config: ClientConfig,
    private val client: HttpClient = ServiceFactory.RestApi.getKtorClientInstance(config),
): CommentClient {

    private val appId: String = config.appId
    private val endpoint: String = ConfigUtils.validateEndpoint(config.endpoint)

    override suspend fun createOrUpdateConversation(request: CreateOrUpdateConversationRequest): Conversation =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Conversation>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun getConversation(conversationid: String): Conversation =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}"
            ) {
                method = HttpMethod.Get
            }

            response.body<ApiResponse<Conversation>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun getConversationByCustomId(customid: String): Conversation =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/find/conversation/bycustomid"
            ) {
                method = HttpMethod.Get

                parameter("customid", customid.encodeURLPath())
            }

            response.body<ApiResponse<Conversation>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun listConversations(
        propertyid: String?,
        cursor: String?,
        limit: Int?,
        sort: SortType?
    ): ListConversations =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations"
            ) {
                method = HttpMethod.Get

                propertyid?.let { parameter("propertyid", it) }
                cursor?.let { parameter("cursor", it) }
                limit?.let { parameter("limit", it) }
                sort?.let { parameter("sort", it.rawValue) }
            }

            response.body<ApiResponse<ListConversations>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun batchGetConversationDetails(
        ids: List<String>?,
        cid: List<String>?,
        entities: List<BatchGetConversationEntity>?
    ): BatchGetConversationDetailsResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/details/batch"
            ) {
                method = HttpMethod.Get

                // Comma-separated (ex. "convo-id1,convo-id2,convo-id3")
                ids?.let { parameter("ids", it.joinToString(",") { str -> str.encodeURLPath() }) }
                cid?.let { customIds ->
                    customIds.forEach { parameter("cid", it.encodeURLPath()) }
                }
                entities?.let { ents ->
                    ents.forEach {
                        parameter("entities", it.rawValue)
                    }
                }
            }

            response.body<ApiResponse<BatchGetConversationDetailsResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun reactToConversationTopic(
        conversationid: String,
        request: ReactToConversationTopicRequest
    ): Conversation =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}/react"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Conversation>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun createComment(
        conversationid: String,
        request: CreateCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun replyToComment(
        conversationid: String,
        replyto: String,
        request: CreateCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${replyto.encodeURLPath()}"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun listReplies(
        conversationid: String,
        commentid: String,
        cursor: String?,
        limit: Int?,
        direction: DirectionType?,
        sort: SortType?,
        includechildren: Boolean?,
        includeinactive: Boolean?
    ): ListComments =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}/replies"
            ) {
                method = HttpMethod.Get

                cursor?.let { parameter("cursor", it) }
                limit?.let { parameter("limit", it) }
                direction?.let { parameter("direction", it.rawValue) }
                sort?.let { parameter("sort", it.rawValue) }
                includechildren?.let { parameter("includechildren", it) }
                includeinactive?.let { parameter("includeinactive", it) }
            }

            response.body<ApiResponse<ListComments>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun getComment(conversationid: String, commentid: String): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}"
            ) {
                method = HttpMethod.Get
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun listComments(
        conversationid: String,
        cursor: String?,
        limit: Int?,
        direction: DirectionType?,
        sort: SortType?,
        includechildren: Boolean?,
        includeinactive: Boolean?
    ): ListComments =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}/comments"
            ) {
                method = HttpMethod.Get

                cursor?.let { parameter("cursor", it) }
                limit?.let { parameter("limit", it) }
                direction?.let { parameter("direction", it.rawValue) }
                sort?.let { parameter("sort", it.rawValue) }
                includechildren?.let { parameter("includechildren", it) }
                includeinactive?.let { parameter("includeinactive", it) }
            }

            response.body<ApiResponse<ListComments>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun listRepliesBatch(
        conversationid: String,
        childlimit: Int?,
        parentids: List<String>,
        includeinactive: Boolean?
    ): ListRepliesBatchResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}/repliesbyparentidbatch"
            ) {
                method = HttpMethod.Get

                childlimit?.let { parameter("childlimit", it) }
                // A comma delimited list of parentids, up to 30.
                parameter("parentids", parentids.joinToString(",") { it.trim().encodeURLPath() })
                includeinactive?.let { parameter("includeinactive", it) }
            }

            response.body<ApiResponse<ListRepliesBatchResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun reactToComment(
        conversationid: String,
        commentid: String,
        request: ReactToCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}/react"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun voteOnComment(
        conversationid: String,
        commentid: String,
        request: VoteOnCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}/vote"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun reportComment(
        conversationid: String,
        commentid: String,
        request: ReportCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}/report"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun updateComment(
        conversationid: String,
        commentid: String,
        request: UpdateCommentRequest
    ): Comment =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}"
            ) {
                method = HttpMethod.Put

                setBody(request)
            }

            response.body<ApiResponse<Comment>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun flagCommentLogicallyDeleted(
        conversationid: String,
        commentid: String,
        userid: String,
        deleted: Boolean,
        permanentifnoreplies: Boolean?
    ): DeleteCommentResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}/setdeleted"
            ) {
                method = HttpMethod.Put

                parameter("userid", userid)
                parameter("deleted", deleted)
                parameter("permanentifnoreplies", permanentifnoreplies ?: false)   // Default to false
            }

            response.body<ApiResponse<DeleteCommentResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun permanentlyDeleteComment(
        conversationid: String,
        commentid: String
    ): DeleteCommentResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}" +
                        "/comments/${commentid.encodeURLPath()}"
            ) {
                method = HttpMethod.Delete
            }

            response.body<ApiResponse<DeleteCommentResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun deleteConversation(conversationid: String): DeleteConversationResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}"
                        + "/comment/conversations/${conversationid.encodeURLPath()}"
            ) {
                method = HttpMethod.Delete
            }

            response.body<ApiResponse<DeleteConversationResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err,
            )
        }

    override suspend fun listCommentsInModerationQueue(
        limit: Int?,
        cursor: String?,
        conversationid: String?,
        filterHandle: String?,
        filterKeyword: String?,
        filterModerationState: CommentFilterModerationState?
    ): ListComments = try {
        val response = client.request(
            "${endpoint}${appId}" + "/comment/moderation/queues/comments"
        ) {
            method = HttpMethod.Get

            cursor?.let { parameter("cursor", it) }
            limit?.let { parameter("limit", it) }
            conversationid?.let { parameter("conversationid", it.encodeURLPath()) }
            filterHandle?.let { parameter("filterHandle", it) }
            filterKeyword?.let { parameter("filterKeyword", it) }
            filterModerationState?.let { parameter("filterModerationState", it.rawValue) }
        }

        response.body<ApiResponse<ListComments>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message,
            err = err,
        )
    }

    override suspend fun approveMessageInQueue(
        commentid: String, request: ApproveMessageRequest
    ): Comment = try {
        val response = client.request(
            "${endpoint}${appId}" + "/comment/moderation/queues" + "/comments/${commentid.encodeURLPath()}/applydecision"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<Comment>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message,
            err = err,
        )
    }

}