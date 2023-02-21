package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ReactToCommentRequest(
    val userid: String,
    val reaction: String,   // ex. "like"(Reaction.Like)
    val reacted: Boolean,
)