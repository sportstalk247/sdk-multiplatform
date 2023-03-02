package com.sportstalk.sdk.core.impl

import com.sportstalk.sdk.core.ConfigUtils
import com.sportstalk.sdk.core.service.CommentModerationService
import com.sportstalk.sdk.model.ApiResponse
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.comment.ApproveMessageRequest
import com.sportstalk.sdk.model.comment.Comment
import com.sportstalk.sdk.model.comment.CommentFilterModerationState
import com.sportstalk.sdk.model.comment.ListComments
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CommentModerationRestApiServiceImpl
internal constructor(
    private val config: ClientConfig,
    private val client: HttpClient,
) : CommentModerationService {

    private val appId: String = config.appId
    private val endpoint: String = ConfigUtils.validateEndpoint(config.endpoint)

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