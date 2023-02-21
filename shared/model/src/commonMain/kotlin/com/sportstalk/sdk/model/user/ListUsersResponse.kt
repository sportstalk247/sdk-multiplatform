package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class ListUsersResponse(
    /** [Kind] */
    val kind: String? = null /* "list.users" */,
    val cursor: String? = null /* "{userId}|" */,
    val users: List<User> = listOf()
)