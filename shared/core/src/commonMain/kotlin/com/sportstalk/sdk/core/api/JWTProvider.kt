package com.sportstalk.sdk.core.api

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

typealias OnRefreshToken = (suspend (String? /* Old Token */) -> String? /* New Token */)

class JWTProvider(
    private var token: String? = null,
    private val tokenRefreshAction: OnRefreshToken? = null
) {
    private val mutex = Mutex()

    private val refreshChannel = Channel<String?>(capacity = Channel.RENDEZVOUS)

    fun getToken(): String? = this.token

    fun setToken(value: String?) {
        this.token = value
    }

    fun refreshToken() {
        refreshChannel.trySendBlocking(this.token)
    }

    /**
     * This allows the refresh callback to execute from within the Coroutine Flow.
     * @return  A cold observable that performs refresh token operation, then internally updates and stores
     * the new token.
     */
    @NativeCoroutines
    fun observe(): Flow<String?> =
        refreshChannel
            .consumeAsFlow()
            .map { oldToken ->
                tokenRefreshAction?.invoke(oldToken)
            }
            .onEach { newToken ->
                mutex.withLock {
                    this@JWTProvider.token = newToken
                }
            }

}