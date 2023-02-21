package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class ReportUserRequest(
    val userid: String? = null,
    /** [ReportType] */
    val reporttype: String? = null // ex. "abuse", "spam"
)