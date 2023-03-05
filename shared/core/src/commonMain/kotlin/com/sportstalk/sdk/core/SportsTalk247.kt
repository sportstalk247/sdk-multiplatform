package com.sportstalk.sdk.core

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.sportstalk.sdk.core.api.ChatClient
import com.sportstalk.sdk.core.api.CommentClient
import com.sportstalk.sdk.core.api.JWTProvider
import com.sportstalk.sdk.core.api.UserClient
import com.sportstalk.sdk.core.impl.ChatClientImpl
import com.sportstalk.sdk.core.impl.CommentClientImpl
import com.sportstalk.sdk.core.impl.UserClientImpl
import com.sportstalk.sdk.model.ClientConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlin.native.concurrent.ThreadLocal

@NativeCoroutineScope
private val coroutineScope: CoroutineScope = MainScope()

@ThreadLocal
object SportsTalk247 {

    /**
     * Factory method to create `UserClient` instance.
     */
    fun UserClient(config: ClientConfig): UserClient =
        UserClientImpl(config)

    /**
     * Factory method to create `ChatClient` instance.
     */
    fun ChatClient(config: ClientConfig): ChatClient =
        ChatClientImpl(config)

    /**
     * Factory method to create `CommentClient` instance.
     */
    fun CommentClient(config: ClientConfig): CommentClient =
        CommentClientImpl(config)

    private var jwtProviders = mutableMapOf<ClientConfig, JWTProvider?>()
    /**
     * Method to set a JWT Provider instance for a specific config.
     */
    fun setJWTProvider(
        config: ClientConfig,
        provider: JWTProvider
    ) {
        jwtProviders[config] = provider
    }

    /**
     * Method to get the JWT Provider instance for the provided config.
     */
    internal fun getJWTProvider(config: ClientConfig): JWTProvider? = jwtProviders[config]

}