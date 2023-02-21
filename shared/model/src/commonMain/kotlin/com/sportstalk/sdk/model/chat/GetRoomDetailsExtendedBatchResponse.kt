package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class GetRoomDetailsExtendedBatchResponse(
    /** [Kind] */
    val kind: String? = null /* "chat.room.list.extendeddetails" */,
    val details: List<Detail> = listOf()
) {

    @kotlinx.serialization.Serializable
    data class Detail(
        val room: ChatRoom? = null,
        val mostrecentmessagetime: String? = null,  /* ISODateTime Format */
        val inroom: Long? = null
    )
}