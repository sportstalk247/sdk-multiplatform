package com.sportstalk.sdk.model.chat.moderation

import com.sportstalk.sdk.model.chat.ChatEvent

@kotlinx.serialization.Serializable
data class ListMessagesNeedingModerationResponse(
    /** [Kind] */
    val kind: String? = null,
    val events: List<ChatEvent> = listOf()
)