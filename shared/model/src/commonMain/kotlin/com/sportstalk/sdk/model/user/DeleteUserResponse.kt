package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class DeleteUserResponse(
    /** [Kind] */
    val kind: String? = null /* "deleted.appuser" */,
    val user: User? = null
)