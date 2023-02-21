package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class DeleteChatRoomResponse(
    /** [Kind] */
    val kind: String? = null /* "deleted.room" */,
    val deletedEventsCount: Long? = null,
    val room: ChatRoom? = null
)