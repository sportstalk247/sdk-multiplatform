package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class SearchUsersRequest(
    val handle: String? = null,
    val name: String? = null,
    val userid: String? = null,
    val limit: Int? = null,
    val cursor: String? = null
)