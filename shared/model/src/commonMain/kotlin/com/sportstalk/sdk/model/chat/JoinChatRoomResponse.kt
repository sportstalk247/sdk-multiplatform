package com.sportstalk.sdk.model.chat

import com.sportstalk.sdk.model.user.User

@kotlinx.serialization.Serializable
data class JoinChatRoomResponse(
    /** [Kind] */
    val kind: String? = null /* "chat.joinroom" */,
    val user: User? = null,
    val room: ChatRoom? = null,
    val eventscursor: GetUpdatesResponse? = null,
    /**
     * The cursor that will be used to fetch paginated previous list of chat events
     */
    val previouseventscursor: String? = null,
    val subscription: ChatSubscription? = null,
)