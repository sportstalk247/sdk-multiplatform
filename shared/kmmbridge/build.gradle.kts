import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    kotlin("native.cocoapods")
    alias(libs.plugins.androidLibrary)
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

    cocoapods {
        name = rootProject.extra["nativeFrameworkName"].toString()
        summary = "Sportstalk247 SDK Multiplatform"
        homepage = "https://github.com/sportstalk247/sdk-multiplatform"
        version = rootProject.extra["packageVersion"].toString()
        ios.deploymentTarget = "15.4"
        framework {
            baseName = rootProject.extra["nativeFrameworkName"].toString()
            isStatic = false

            export(project(":shared:core"))
            export(project(":shared:model"))
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlin.experimental.ExperimentalNativeApi")
            }
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(project(":shared:model"))
            api(project(":shared:core"))
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
                    "-Xexport-kdoc",
                )
            }
        }
    }
}

android {
    namespace = "com.sportstalk.sdk.kmmbridge"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
