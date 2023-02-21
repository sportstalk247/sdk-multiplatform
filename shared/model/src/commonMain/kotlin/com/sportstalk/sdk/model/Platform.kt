package com.sportstalk.sdk.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform