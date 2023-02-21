package com.sportstalk.sdk.model.chat

import com.sportstalk.sdk.model.user.User

@kotlinx.serialization.Serializable
data class ListChatRoomParticipantsResponse(
    /** [Kind] */
    val kind: String? = null /* "list.chatparticipants" */,
    val cursor: String? = null,
    val participants: List<ChatRoomParticipant> = listOf(),
)

@kotlinx.serialization.Serializable
data class ChatRoomParticipant(
    /** [Kind] */
    val kind: String? = null /* "chat.participant" */,
    val user: User? = null,
)