package com.sportstalk.sdk.model

import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject

@kotlinx.serialization.Serializable
data class SportsTalkException(
    val kind: String? = null,
    override val message: String? = null,
    val code: Int? = null,
    val data: JsonObject? = null,
    @Transient
    val err: Throwable? = null
): Exception(message, err)