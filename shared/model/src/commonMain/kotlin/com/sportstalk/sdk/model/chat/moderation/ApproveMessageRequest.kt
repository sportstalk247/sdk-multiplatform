package com.sportstalk.sdk.model.chat.moderation

@kotlinx.serialization.Serializable
data class ApproveMessageRequest(
    val approve: Boolean
)