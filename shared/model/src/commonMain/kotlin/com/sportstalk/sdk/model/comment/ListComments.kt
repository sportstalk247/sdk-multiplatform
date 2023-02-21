package com.sportstalk.sdk.model.comment

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ListComments(
    val kind: String? = null, // "list.comments"
    val cursor: String? = null,
    val more: Boolean? = null,
    val itemcount: Int? = null,
    val conversation: Conversation? = null,
    val comments: List<Comment> = listOf()
)