package com.sportstalk.sdk.model.comment

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
@CommonParcelize
enum class BatchGetConversationEntity(val rawValue: String) : CommonParcelable {
    @SerialName("reactions")
    Reactions("reactions"),

    @SerialName("likecount")
    LikeCount("likecount"),

    @SerialName("commentcount")
    CommentCount("commentcount"),
}