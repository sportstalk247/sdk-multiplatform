package com.sportstalk.sdk.model.comment

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class CommentFilterModerationState(val rawValue: String) {

    @SerialName("approved")
    Approved("approved"),

    @SerialName("rejected")
    Rejected("rejected"),

    @SerialName("pending")
    Pending("pending"),

    @SerialName("flagged")
    Flagged("flagged"),

}