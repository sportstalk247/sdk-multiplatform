package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class MuteUserInRoomRequest(
    val userid: String,
    val applyeffect: Boolean,
    val expireseconds: Long? = null     // in seconds
)