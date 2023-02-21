package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class BounceUserResponse(
    val kind: String? = null, // "chat.bounceuser"
    val event: ChatEvent? = null,
    val room: ChatRoom? = null
)