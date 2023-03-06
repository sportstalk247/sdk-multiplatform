import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    kotlin("native.cocoapods")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kmmBridge)
    id("maven-publish")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations.get("main").kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            apiVersion = "1.4"
            languageVersion = "1.4"
        }
    }

    cocoapods {
        name = "Sportstalk247Kit"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "2.0.0-beta01"
        ios.deploymentTarget = "15.4"
        framework {
            baseName = "Sportstalk247Kit"
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
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
    // mavenPublishArtifacts()

    // Preferred Publish Versioning
    /*githubReleaseVersions()*/
    /*gitTagVersions()*/
    /*timestampVersions()*/
    manualVersions()

    spm()
    cocoapods("git@gitlab.com:sportstalk247/sdk-kmm.git")
    //etc
}

publishing {
    repositories {
        maven {
            // https://docs.gitlab.com/ee/user/packages/gradle_repository/
            // url = uri("https://gitlab.example.com/api/v4/projects/<PROJECT_ID>/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "GitlabPackageRegistryToken"
                value =
                    findProperty("gitLabPrivateToken") as String? // the variable resides in $GRADLE_USER_HOME/gradle.properties
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }
        }

    }
}
