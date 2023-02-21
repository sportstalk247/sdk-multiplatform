package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class DeleteCommentResponse(
    val kind: String? = null, // "delete.comment"
    val permanentdelete: Boolean? = null,
    val comment: Comment? = null
)