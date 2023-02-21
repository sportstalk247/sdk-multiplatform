package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ApproveMessageRequest(
    val approve: Boolean,    // If set to true, approves the message. Otherwise, reject.
)
