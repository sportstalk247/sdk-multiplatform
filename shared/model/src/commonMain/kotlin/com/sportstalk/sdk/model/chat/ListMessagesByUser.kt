package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ListMessagesByUser(
    val kind: String? = null,
    val cursor: String? = null,
    val events: List<ChatEvent> = listOf()
)