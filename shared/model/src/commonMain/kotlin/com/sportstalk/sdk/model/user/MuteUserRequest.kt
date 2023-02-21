package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class MuteUserRequest(
    val applyeffect: Boolean,
    val expireseconds: Long? = null
)