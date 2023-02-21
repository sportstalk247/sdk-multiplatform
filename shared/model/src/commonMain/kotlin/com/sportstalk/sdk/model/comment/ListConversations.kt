package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ListConversations(
    val kind: String? = null, /* "list.commentconversations" */
    val cursor: String? = null,
    val more: Boolean? = null,
    val conversations: List<Conversation> = listOf()
)