package com.sportstalk.sdk.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform