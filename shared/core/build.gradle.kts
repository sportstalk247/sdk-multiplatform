import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.googleKsp)
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
        publishLibraryVariants("release")
    }
    jvmToolchain(17)

    /**
     * Prepare iOS Target
     */
    when {
        // Only build for iOS Simulator, run it like: ./gradlew build -PiosOnlySimulator in the terminal
        project.hasProperty("iosOnlySimulator") -> listOf(iosSimulatorArm64("ios"))
        // Only build for iOS Device, run it like: ./gradlew build -PiosOnlyDevice in the terminal
        project.hasProperty("iosOnlyDevice") -> listOf(iosArm64("ios"))
        // Build for both iOS Simulator, iOS Devices and Apple Silicon, run it like: ./gradlew build -PiosOnlyDevice
        // in the terminal or simply hit the Make Module/Project button in Android Studio
        else -> {
            listOf(
                iosArm64(),
                iosSimulatorArm64()
            )
        }
    }
    applyDefaultHierarchyTemplate()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.experimental.ExperimentalNativeApi")
                optIn("kotlinx.coroutines.FlowPreview")
                optIn("kotlin.time.ExperimentalTime")
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okHttp)
        }

        commonMain.dependencies {
            api(project(":shared:model"))

            implementation(libs.coroutines.core)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.dateTime)

            implementation(libs.bundles.ktor.common)

            implementation(kotlin("test"))
        }

        commonTest.dependencies {
            implementation(libs.bundles.ktor.common)
            implementation(libs.coroutines.test)
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        iosTest.dependencies {
            // ...
        }
    }
}

android {
    namespace = "com.sportstalk.sdk.core"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
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
        ProcessBuilder("xcrun", "simctl", "boot", device).start().waitFor()
        ProcessBuilder("xcrun", "simctl", "spawn", device, binary.absolutePath).start().waitFor()
        ProcessBuilder("xcrun", "simctl", "shutdown", device).start().waitFor()
    }
}