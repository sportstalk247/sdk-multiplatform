package com.sportstalk.sdk.model.chat

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ReportUserInRoomRequest(
    /** the userid of the person doing the report */
    @SerialName("userid")
    val reporterid: String,
    /* [ReportType] */
    val reporttype: String
)