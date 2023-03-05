buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.10-1.0.9")
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:1.0.0-ALPHA-5")
        classpath("co.touchlab.faktory.kmmbridge:co.touchlab.faktory.kmmbridge.gradle.plugin:0.3.5")
    }
}

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
