package com.sportstalk.sdk.core

import com.sportstalk.sdk.core.impl.*
import com.sportstalk.sdk.core.service.*
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object ServiceFactory {

    object RestApi {

        internal val json: Json by lazy {
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        private val ktorClientInstances: HashMap<String /* config.apiTOken */, HttpClient> = hashMapOf()

        fun getKtorClientInstance(
            config: ClientConfig
        ): HttpClient {
            return if(ktorClientInstances.containsKey(config.apiToken)) {
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
            if(instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                UserRestApiServiceImpl(
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
            if(instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                ChatRestApiServiceImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

    object ChatModeration {
        private val instances: HashMap<ClientConfig, ChatModerationService> = hashMapOf()

        internal fun get(config: ClientConfig): ChatModerationService =
            if(instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                ChatModerationRestApiServiceImpl(
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
            if(instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                CommentRestApiServiceImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

    object CommentModeration {
        private val instances: HashMap<ClientConfig, CommentModerationService> = hashMapOf()

        internal fun get(config: ClientConfig): CommentModerationService =
            if(instances.containsKey(config)) {
                instances[config]!!
            } else {
                val ktorClient = RestApi.getKtorClientInstance(config)
                // REST API Implementation
                CommentModerationRestApiServiceImpl(
                    config = config,
                    client = ktorClient,
                ).also {
                    instances[config] = it
                }
            }

    }

}