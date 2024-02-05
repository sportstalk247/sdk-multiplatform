package com.sportstalk.sdk.model.comment

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import com.sportstalk.sdk.model.reactions.Reaction

@CommonParcelize
@kotlinx.serialization.Serializable
data class Conversation(
    val kind: String? = null, // "comment.conversation"
    val appid: String? = null,
    val owneruserid: String? = null,
    val conversationid: String? = null,
    val property: String? = null,   // "sportstalk247.com/apidemo"
    val moderation: String? = null, /* "pre"/"post"/"na" */
    val maxreports: Long? = null, // OPTIONAL, defaults to 3
    val enableprofanityfilter: Boolean? = null, // OPTIONAL, defaults to true
    val title: String? = null,
    val maxcommentlen: Long? = null,
    val commentcount: Long? = null,
    val replycount: Long? = null,
    val reactioncount: Long? = null,
    val reactions: List<Reaction>? = null,
    val likecount: Long? = null,
    val open: Boolean? = null, // OPTIONAL, defaults to true
    val added: String? = null, // OPTIONAL, Example value: "2020-05-02T08:51:53.8140055Z"
    val whenmodified: String? = null, // OPTIONAL, Example value: "2020-05-02T08:51:53.8140055Z"
    val customtype: String? = null,
    val customid: String? = null,
    val customtags: List<String>? = null,
    val custompayload: String? = null,
    val customfield1: String? = null,
    val customfield2: String? = null
): CommonParcelable