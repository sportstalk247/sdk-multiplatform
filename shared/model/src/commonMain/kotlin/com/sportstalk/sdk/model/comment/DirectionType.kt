package com.sportstalk.sdk.model.comment

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class DirectionType(val rawValue: String) {

    @SerialName("forward")
    Forward("forward"),

    @SerialName("backward")
    Backward("backward"),

}