package com.sportstalk.sdk.core

import com.sportstalk.sdk.model.ClientConfig
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

internal actual fun getHttpClientEngine(config: ClientConfig): HttpClientEngine =
    Darwin.create {
        configureSession {
            setAllowsCellularAccess(true)
            setAllowsConstrainedNetworkAccess(true)
            setAllowsExpensiveNetworkAccess(true)
            setTimeoutIntervalForRequest(10000.0)
            setTimeoutIntervalForResource(10000.0)
        }
    }

internal actual val IS_DEVELOPMENT_MODE: Boolean
    get() = Platform.isDebugBinary