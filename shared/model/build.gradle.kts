import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-parcelize")
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

//    tasks.withType<KotlinCompile>().configureEach {
//        kotlinOptions {
//            apiVersion = "1.9"/*"1.4"*/
//            languageVersion = "1.9"/*"1.4"*/
//        }
//    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.experimental.ExperimentalObjCName")
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            // ...
        }

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.dateTime)

            implementation(kotlin("test"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        iosMain.dependencies {
            // ...
        }

        iosTest.dependencies {
            // ...
        }
    }

    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.addAll(
                    "-Xexpect-actual-classes",
                    "-P", "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.sportstalk.sdk.model.CommonParcelize",
                )
            }
        }
    }
}

android {
    namespace = "com.sportstalk.sdk.model"
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
