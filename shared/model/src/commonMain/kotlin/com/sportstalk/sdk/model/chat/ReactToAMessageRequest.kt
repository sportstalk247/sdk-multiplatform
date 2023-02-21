package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ReactToAMessageRequest(
    val userid: String,
    val reaction: String,
    val reacted: Boolean
)