package com.sportstalk.sdk.model.comment

@kotlinx.serialization.Serializable
data class ListRepliesBatchResponse(
    val kind: String? = null, // "list.repliesbyparentid"
    val repliesgroupedbyparentid: List<CommentReplyGroup> = listOf(),
) {

    @kotlinx.serialization.Serializable
    data class CommentReplyGroup(
        val kind: String? = null, // "list.commentreplygroup"
        val parentid: String? = null,
        val comments: List<Comment> = listOf(),
    )

}
