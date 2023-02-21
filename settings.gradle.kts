pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
            credentials {
                username = "jp_34fduc0vcrkfd7rtgblm22bvru"
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "Sportstalk247_Kotlin_Multiplatform"
include(":shared:model")
include(":shared:core")
include(":shared:kmmbridge")
