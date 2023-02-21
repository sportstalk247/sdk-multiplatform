package com.sportstalk.sdk.kmmbridge

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform