package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ShadowBanUserInRoomRequest(
    val userid: String,
    val applyeffect: Boolean,
    val expireseconds: Long? = null     // in seconds
)