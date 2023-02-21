package com.sportstalk.sdk.model.comment

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import com.sportstalk.sdk.model.reactions.Reaction
import com.sportstalk.sdk.model.reports.Report
import com.sportstalk.sdk.model.user.User
import kotlinx.serialization.Serializable

@CommonParcelize
@kotlinx.serialization.Serializable
data class Comment(
    val kind: String? = null, // "comment.comment"
    val id: String? = null,
    val appid: String? = null,
    val conversationid: String? = null,
    /** [CommentType] */
    val commenttype: String? = null,
    val added: String? = null, // OPTIONAL, Example value: "2020-05-02T08:51:53.8140055Z"
    val modified: String? = null, // OPTIONAL, Example value: "2020-05-02T08:51:53.8140055Z"
    val tsunix: Long? = null,       // Date time ms
    val userid: String? = null,
    val user: User? = null,
    val body: String? = null,
    val originalbody: String? = null,
    val hashtags: List<String>? = null,
    val shadowban: Boolean? = null,
    val customtype: String? = null,
    val customid: String? = null,
    val custompayload: String? = null,
    val customtags: List<String>? = null,
    val customfield1: String? = null,
    val customfield2: String? = null,
    val edited: Boolean? = null,
    val censored: Boolean? = null,
    val deleted: Boolean? = null,
    val parentid: String? = null,
    val hierarchy: List<String>? = null,
    /*val mentions: List<String>? = null,*/
    val reactions: List<Reaction>? = null,
    val likecount: Long? = null,
    val replycount: Long? = null,
    val votecount: Long? = null,
    val votescore: Long? = null,
    val votes: List<VoteDetail>? = null,
    val moderation: String? = null,
    val active: Boolean? = null,
    val reports: List<Report>? = null,
): CommonParcelable

object CommentType {
    const val COMMENT = "comment"
}