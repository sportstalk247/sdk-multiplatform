package com.sportstalk.sdk.model.chat

@kotlinx.serialization.Serializable
data class DeleteEventResponse(
    /**
     *  TODO:: [Kind.DELETED_COMMENT]
     */
    val kind: String? = null,
    val permanentdelete: Boolean? = null,
    val event: ChatEvent? = null
)