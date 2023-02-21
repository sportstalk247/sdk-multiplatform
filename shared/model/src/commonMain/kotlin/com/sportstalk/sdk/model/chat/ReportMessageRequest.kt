package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ReportMessageRequest(
    val reporttype: String,
    val userid: String
)