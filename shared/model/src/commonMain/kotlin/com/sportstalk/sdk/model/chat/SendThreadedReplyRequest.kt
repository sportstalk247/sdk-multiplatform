package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class SendThreadedReplyRequest(
    val body: String,
    val userid: String,
    val customtype: String? = null,
    val customid: String? = null,
    val custompayload: String? = null
)