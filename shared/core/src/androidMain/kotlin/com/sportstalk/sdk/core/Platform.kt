package com.sportstalk.sdk.core

import com.sportstalk.sdk.model.ClientConfig
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

internal actual fun getHttpClientEngine(config: ClientConfig): HttpClientEngine =
    OkHttp.create {
        // TODO:: OkHttp Config
    }
