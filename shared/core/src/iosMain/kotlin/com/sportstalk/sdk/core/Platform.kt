package com.sportstalk.sdk.core

import com.sportstalk.sdk.model.ClientConfig
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

internal actual fun getHttpClientEngine(config: ClientConfig): HttpClientEngine =
    Darwin.create {
        // TODO:: OkHttp Config
    }

internal actual val IS_DEVELOPMENT_MODE: Boolean
    get() = Platform.isDebugBinary