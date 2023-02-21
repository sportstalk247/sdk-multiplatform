package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class ExitChatRoomRequest(
    val userid: String
)