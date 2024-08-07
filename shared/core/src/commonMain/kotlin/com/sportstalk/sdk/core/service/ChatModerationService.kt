package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.model.chat.ChatEvent
import com.sportstalk.sdk.model.chat.moderation.ListMessagesNeedingModerationResponse

interface ChatModerationService {

    /**
     * [POST] /{{api_appid}}/chat/moderation/queues/events/{{eventId}}/applydecision
     * - https://apiref.sportstalk247.com/?version=latest#6f9bf714-5b3b-48c9-87d2-eb2e12d2bcbf
     * - APPROVES a message in the moderation queue.
     */
    suspend fun approveMessage(eventId: String, approve: Boolean): ChatEvent

    /**
     * [POST] /{{api_appid}}/chat/moderation/queues/events
     * - https://apiref.sportstalk247.com/?version=latest#bcdbda1b-e495-46c9-8fe9-c5dc6a4c1756
     * - List all the messages in the moderation queue
     */
    suspend fun listMessagesNeedingModeration(
        roomId: String? = null /* Filter to which specific roomId to fetch event(s) from */,
        limit: Int? = null /* Defaults to 200 on backend API server */,
        cursor: String? = null
    ): ListMessagesNeedingModerationResponse

    /**
     * [POST] /{{api_appid}}/chat/rooms/{{chatroomid}}/commands/purge
     * - https://apiref.sportstalk247.com/?version=latest#04ffee45-a3e6-49b8-8968-46b219020b66
     * - Executes a command in a chat room to purge all messages for a user
     */
    suspend fun purgeUserMessages(
        chatRoomId: String,
        userId: String,
        byUserId: String,
    )

}