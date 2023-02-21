package com.sportstalk.sdk.model.comment

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class DeleteConversationResponse(
    val kind: String? = null, // "delete.comment"
    val conversationid: String? = null,
    val userid: String? = null,
    val deletedconversations: Long? = null,
    val deletedcomments: Long? = null,
)