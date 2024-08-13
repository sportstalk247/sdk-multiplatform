package com.sportstalk.sdk.model.chat.moderation

@kotlinx.serialization.Serializable
data class PurgeUserMessagesRequest(
    val userid: String,
    val byuserid: String,
)