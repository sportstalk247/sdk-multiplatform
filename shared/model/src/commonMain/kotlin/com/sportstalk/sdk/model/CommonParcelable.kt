package com.sportstalk.sdk.model

/**
 *
 * Common parcelable implementation for android
 */

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable