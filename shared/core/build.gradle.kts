@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.kmpNativeCoroutines)
    id("maven-publish")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        // Publish an Android library(https://kotlinlang.org/docs/multiplatform-publish-lib.html#publish-an-android-library)
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
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
//    fileSuffix = ""
}

android {
    namespace = "com.sportstalk.sdk.core"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}

group = rootProject.extra["packageGroup"].toString()
version = rootProject.extra["packageVersion"].toString()
//
//// Avoid duplicate publications(https://kotlinlang.org/docs/multiplatform-publish-lib.html#avoid-duplicate-publications)
//val publicationsFromMainHost =
//    listOf(
//        android(),
//        // Later add other targets
//    ).map { it.name } + "-kmm"
//publishing {
//    publications {
//        matching { it.name in publicationsFromMainHost }.all {
//            val targetPublication = this@all
//            tasks.withType<AbstractPublishToMaven>()
//                .matching { it.publication == targetPublication }
//                .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
//        }
//    }
//}
//
//publishing {
//    repositories {
//        maven {
//            // https://docs.gitlab.com/ee/user/packages/gradle_repository/
//            url = uri("https://com.sportstalk.sdk/api/v4/projects/43716798/packages/maven")
//            name = "GitLab"
//            credentials(HttpHeaderCredentials::class) {
//                name = "KMM Publish"//"GitlabPackageRegistryToken"
//                value =
//                    findProperty("gitLabPrivateToken") as String? // the variable resides in $GRADLE_USER_HOME/gradle.properties
//            }
//            authentication {
//                create("header", HttpHeaderAuthentication::class)
//            }
//        }
//
//    }
//}
