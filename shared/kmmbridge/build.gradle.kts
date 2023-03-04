import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines")
    id("maven-publish")
    id("com.chromaticnoise.multiplatform-swiftpackage")
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
        ios.deploymentTarget = "14.1"
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

//
// KMPNativeCoroutines
//
nativeCoroutines {
    suffix = "Apple"
}

//
// Maven publish
//
/*publishing {
    repositories {
        maven {
            name = "Snapshot"
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            if (version.toString().endsWith("SNAPSHOT")) setUrl { snapshotRepoUrl }
            else setUrl { releasesRepoUrl }
            credentials {
                username = ""
                password = ""
            }
        }
    }

    publications {
        create<MavenPublication>("Maven") {
            groupId = "com.sportstalk.sdk"
            artifactId = "kmm"
            version = "2.0.0-beta01"
            from(components["kotlin"])
        }
        withType<MavenPublication> {
            pom {
                packaging = "jar"
                name.set("Sportstalk247 SDK - KMM")
                description.set("Sportstalk247 SDK for Android and iOS")
                url.set("https://www.sportstalk247.com/")
//                licenses {
//                    license {
//                        name.set("MIT license")
//                        url.set("https://opensource.org/licenses/MIT")
//                    }
//                }
                issueManagement {
                    system.set("Gitlab")
                    url.set("https://gitlab.com/sportstalk247/sdk-kmm")
                }
                scm {
                    connection.set("scm:git:git@gitlab.com:sportstalk247/sdk-kmm.git")
                    developerConnection.set("scm:git:git@gitlab.com:sportstalk247/sdk-kmm.git")
                    url.set("https://gitlab.com/sportstalk247/sdk-kmm")
                }
                developers {
                    developer {
                        name.set("Lawrence C. Cendaña")
                        email.set("lcendana@sportstalk247.com")
                    }
                }
            }
        }
    }
}*/

//signing {
//    useInMemoryPgpKeys(
//        "",
//        ""
//    )
//    sign(publishing.publications)
//}

//
// Swift Package Manager
//
multiplatformSwiftPackage {
    packageName("Sportstalk247Kit")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
        macOS{ v("10_15") }
    }

    // Distribute locally for now
    distributionMode { local() }
    // distributionMode { remote("https://example.com") }

//    buildConfiguration { debug() }
//    buildConfiguration { release() }
}

android {
    namespace = "com.sportstalk.sdk.kmmbridge"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
//        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}