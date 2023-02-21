package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class BanUserRequest(
    val applyeffect: Boolean,
    val expireseconds: Long? = null,
)