pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
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
