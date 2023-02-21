package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class BounceUserRequest(
    val userid: String,
    val bounce: Boolean,
    val announcement: String? = null
)