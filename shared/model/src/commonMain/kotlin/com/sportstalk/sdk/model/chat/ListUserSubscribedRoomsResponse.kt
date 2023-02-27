package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ListUserSubscribedRoomsResponse(
    /** [Kind] */
    val kind: String? = null /* "chat.list.userroomsubscriptions" */,
    val cursor: String? = null,
    val more: Boolean? = null,
    val itemcount: Long? = null,
    val subscriptions: List<Data> = emptyList(),
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val kind: String? = null,   /* "chat.subscriptionandstatus" */
        val subscription: ChatSubscription? = null,
        val roomstatus: RoomStatus? = null,
    )

    @kotlinx.serialization.Serializable
    data class RoomStatus(
        val kind: String? = null,   /* "chat.roomstatus" */
        val messagecount: Long? = null,
        val participantcount: Long? = null,
        val newestmessage: ChatEvent? = null,
    )
}
