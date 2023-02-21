package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class SetShadowBanStatusRequest(
    val applyeffect: Boolean,
    val expireseconds: Long? = null     // in seconds
)