package com.sportstalk.sdk.core

import com.sportstalk.sdk.core.api.JWTProvider
import com.sportstalk.sdk.model.ClientConfig

object SportsTalk247 {


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