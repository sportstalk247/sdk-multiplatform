package com.sportstalk.sdk.core

import com.sportstalk.sdk.model.ClientConfig
import io.ktor.client.engine.*

internal expect fun getHttpClientEngine(config: ClientConfig): HttpClientEngine

internal expect val IS_DEVELOPMENT_MODE: Boolean
