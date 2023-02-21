package com.sportstalk.sdk.model.comment

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class VoteType(val rawValue: String) {

    @SerialName("up")
    Up("up"),

    @SerialName("down")
    Down("down"),

    @SerialName("none")
    None("none"),

}