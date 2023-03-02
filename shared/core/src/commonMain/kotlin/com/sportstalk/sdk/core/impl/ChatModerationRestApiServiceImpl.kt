package com.sportstalk.sdk.core.impl

import com.sportstalk.sdk.core.ConfigUtils
import com.sportstalk.sdk.core.service.ChatModerationService
import com.sportstalk.sdk.model.ApiResponse
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.chat.ChatEvent
import com.sportstalk.sdk.model.chat.moderation.ApproveMessageRequest
import com.sportstalk.sdk.model.chat.moderation.ListMessagesNeedingModerationResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ChatModerationRestApiServiceImpl
internal constructor(
    private val config: ClientConfig,
    private val client: HttpClient,
) : ChatModerationService {

    private val appId: String = config.appId
    private val endpoint: String = ConfigUtils.validateEndpoint(config.endpoint)

    override suspend fun approveMessage(eventId: String, approve: Boolean): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/moderation/queues/" + "events/${eventId}/applydecision"
        ) {
            method = HttpMethod.Post
            setBody(
                ApproveMessageRequest(approve = approve)
            )
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message,
            err = err,
        )
    }

    override suspend fun listMessagesNeedingModeration(
        roomId: String?, limit: Int?, cursor: String?
    ): ListMessagesNeedingModerationResponse = try {
        val response = client.request(
            "${endpoint}${appId}/chat/moderation/queues/events"
        ) {
            method = HttpMethod.Get

            parameter("roomId", roomId)
            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListMessagesNeedingModerationResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }
}