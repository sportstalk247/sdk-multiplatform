package com.sportstalk.sdk.model.comment

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class BatchGetConversationDetailsResponse(
    val kind: String? = null, /* "list.comment.conversation.details" */
    val itemcount: Long? = null,
    val conversations: List<Conversation> = listOf(),
)
