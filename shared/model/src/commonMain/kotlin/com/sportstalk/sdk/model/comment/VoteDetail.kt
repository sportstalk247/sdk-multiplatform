package com.sportstalk.sdk.model.comment

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import com.sportstalk.sdk.model.user.User

@CommonParcelize
@kotlinx.serialization.Serializable
data class VoteDetail(
    val type: VoteType? = null,
    val count: Long? = null,
    val users: List<User>? = null,
): CommonParcelable
