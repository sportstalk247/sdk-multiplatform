package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class UpdateCommentRequest(
    val userid: String,
    val body: String,
)