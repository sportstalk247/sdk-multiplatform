package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ReactToConversationTopicRequest(
    val userid: String,
    val reaction: String,   // ex. "like"(ReactionType.LIKE)
    val reacted: Boolean,
)
