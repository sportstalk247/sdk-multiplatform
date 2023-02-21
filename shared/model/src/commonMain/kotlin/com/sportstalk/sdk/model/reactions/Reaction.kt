package com.sportstalk.sdk.model.reactions

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import com.sportstalk.sdk.model.user.User

@CommonParcelize
@kotlinx.serialization.Serializable
data class Reaction(
    val type: String? = null,
    val count: Long? = null,
    val users: List<User> = listOf()
): CommonParcelable

object ReactionType {
    const val LIKE = "like"
}
