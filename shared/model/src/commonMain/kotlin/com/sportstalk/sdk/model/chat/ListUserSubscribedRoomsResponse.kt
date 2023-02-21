package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ListUserSubscribedRoomsResponse(
    /** [Kind] */
    val kind: String? = null /* "chat.list.userroomsubscriptions" */,
    val cursor: String? = null,
    val more: Boolean? = null,
    val itemcount: Long? = null,
    val subscriptions: List<ChatSubscription> = emptyList()
)
