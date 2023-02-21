package com.sportstalk.sdk.model

@kotlinx.serialization.Serializable
data class ApiResponse<T>(
    /** [Kind] */
    val kind: String? = null,
    val message: String? = null,
    val code: Int? = null,
    /*val errors: Map<String, Any> = mapOf(),*/
    val data: T? = null
)