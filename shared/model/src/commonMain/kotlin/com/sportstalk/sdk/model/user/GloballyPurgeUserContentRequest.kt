package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class GloballyPurgeUserContentRequest(
    private val byuserid: String,
)