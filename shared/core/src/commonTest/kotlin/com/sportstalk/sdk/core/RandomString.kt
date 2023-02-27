package com.sportstalk.sdk.core

import kotlin.random.Random

object RandomString {

    private val charPool : List<Char> = ('A'..'F') + ('0'..'9')

    fun make(length: Int): String =
        (1..length)
            .map {
                Random.nextInt(0, charPool.size).let { charPool[it] }
            }
            .joinToString("")

}