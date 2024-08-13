package com.sportstalk.sdk.core

import com.sportstalk.sdk.core.impl.ChatClientImpl
import com.sportstalk.sdk.core.impl.CommentClientImpl
import com.sportstalk.sdk.core.impl.UserClientImpl
import com.sportstalk.sdk.core.service.ChatService
import com.sportstalk.sdk.core.service.CommentService
import com.sportstalk.sdk.core.service.UserService
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.collections.set

internal object ServiceFactory {

    object RestApi {

        internal val json: Json by lazy {
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        private val ktorClientInstances: HashMap<String, HttpClient> =
            hashMapOf()

        fun getKtorClientInstance(
            config: ClientConfig
        ): HttpClient {
            return if (ktorClientInstances.containsKey(config.apiToken)) {
                ktorClientInstances[config.apiToken]!!
            } else {
                HttpClient(
                    getHttpClientEngine(config)
                ) {
                    expectSuccess = true
                    developmentMode = IS_DEVELOPMENT_MODE

                    defaultRequest {
                        headers {
                            append("x-api-token", config.apiToken)
                            val jwtProvider = SportsTalk247.getJWTProvider(config)
                            val customJWT = jwtProvider?.getToken()
                            customJWT?.trim()?.takeIf { it.isNotEmpty() }?.let { jwt ->
                                append("Authorization", "Bearer $jwt")
                            }
                        }

                        this.accept(ContentType.Application.Json)
                        this.contentType(ContentType.Application.Json)
                    }

                    install(ContentNegotiation) {
                        this@install.json(json)
                    }

                    install(HttpCallValidator) {
                        handleResponseExceptionWithRequest { cause, _ ->
                            throw when (cause) {
                                is ResponseException -> cause.response.body<SportsTalkException>()
                                else -> cause
                            }
                        }
                    }

                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(HttpTimeout) {
                        val timeout = 10_000L
                        connectTimeoutMillis = timeout
                        requestTimeoutMillis = timeout
                        socketTimeoutMillis = timeout
                    }

                    // TODO:: Add Custom Interceptor(s) in here...
                }
            }
        }

    }

    object User {
        private val instances: HashMap<ClientConfig, UserService> = hashMapOf()

        internal fun get(config: ClientConfig): UserService =
            if (instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                UserClientImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

    object Chat {
        private val instances: HashMap<ClientConfig, ChatService> = hashMapOf()

        internal fun get(config: ClientConfig): ChatService =
            if (instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                ChatClientImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

    object Comment {
        private val instances: HashMap<ClientConfig, CommentService> = hashMapOf()

        internal fun get(config: ClientConfig): CommentService =
            if (instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                CommentClientImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

}