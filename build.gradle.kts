buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:1.0.0-ALPHA-4")
        classpath("com.chromaticnoise.multiplatform-swiftpackage:com.chromaticnoise.multiplatform-swiftpackage.gradle.plugin:2.0.3")
        classpath("co.touchlab.faktory.kmmbridge:co.touchlab.faktory.kmmbridge.gradle.plugin:0.3.5")
    }
}

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //trick: for the same plugin versions in all sub-modules
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.googleKsp) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
