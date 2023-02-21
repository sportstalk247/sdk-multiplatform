package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class VoteOnCommentRequest(
    val vote: VoteType,
    val userid: String,
)
