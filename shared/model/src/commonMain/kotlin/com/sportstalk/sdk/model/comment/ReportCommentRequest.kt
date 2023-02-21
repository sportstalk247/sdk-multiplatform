package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ReportCommentRequest(
    val userid: String,
    /** [ReportType] */
    val reporttype: String,
)