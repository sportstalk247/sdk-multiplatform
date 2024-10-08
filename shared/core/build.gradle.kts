import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.kmpNativeCoroutines)
    id("maven-publish")
    signing
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }

        // Publish an Android library(https://kotlinlang.org/docs/multiplatform-publish-lib.html#publish-an-android-library)
        publishLibraryVariants("release", "debug")
    }
    jvmToolchain(17)

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.experimental.ExperimentalNativeApi")
                optIn("kotlinx.coroutines.FlowPreview")
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared:model"))

                implementation(libs.coroutines.core)

                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.dateTime)

                implementation(libs.bundles.ktor.common)

                implementation(kotlin("test"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.ktor.common)
                implementation(libs.coroutines.test)
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okHttp)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

//
// KMPNativeCoroutines
//
nativeCoroutines {
    suffix = "Async"
    fileSuffix = "Native"
}

android {
    namespace = "com.sportstalk.sdk.core"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

//
// Kotlin/Native: NSURLConnection HTTPS requests fail in iOS tests
// - https://youtrack.jetbrains.com/issue/KT-38317
//
tasks.register("runIosTests")  {
    val device = project.findProperty("iosDevice") as? String ?: "iPhone 8"
    /*dependsOn("linkDebugTestIosArm64")*/
    dependsOn("linkDebugTestIosSimulatorArm64")
    /*dependsOn("linkDebugTestIosX64")*/
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'ios' on an iOS simulator"

    doLast {
        val  binary = (kotlin.targets["iosArm64"] as KotlinNativeTarget).binaries.getTest("DEBUG").outputFile
        exec {
            commandLine = listOf(
                "xcrun simctl boot \"$device\"",
                "xcrun simctl spawn \"$device\" ${binary.absolutePath}",
                "xcrun simctl shutdown \"$device\"",
            )
        }
    }
}