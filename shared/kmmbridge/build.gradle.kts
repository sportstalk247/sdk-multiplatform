import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    kotlin("native.cocoapods")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kmmBridge)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"/*"1.8"*/
            }
        }
    }
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations.get("main").kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            apiVersion = "1.9"/*"1.4"*/
            languageVersion = "1.9"/*"1.4"*/

            jvmTarget = "17"/*JavaVersion.VERSION_1_8.toString()*/
        }
    }

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
        val commonMain by getting {
            dependencies {
                api(project(":shared:model"))
                api(project(":shared:core"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.sportstalk.sdk.kmmbridge"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
//        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}

//
// KMM Bridge Plugin Setup
//
kmmbridge {
    // TODO:: Comment out for now... For the meantime, do local dev workflow.
//     mavenPublishArtifacts()

    // Preferred Publish Versioning
    /*githubReleaseVersions()*/
    /*gitTagVersions()*/
    /*timestampVersions()*/
    manualVersions()

    spm()
    cocoapods("git@github.com:sportstalk247/sdk-multiplatform.git")
    //etc
}

