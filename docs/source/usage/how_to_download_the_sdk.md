# How to download the SDK

## Download from Repository

The SportsTalk SDK has been published into **jitpack.io**.
In order to use it in your application, just do the following:

``` tabs::

    .. code-tab:: groovy Android(Groovy)
        
        // 1. Add the following in root  **build.gradle** file, repositories section
        allprojects {
            repositories {
                // ...
                mavenCentral()
                maven {
                    url "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                }
                // ...
            }
        }
        
        // 2. Add the following lines in your module **build.gradle** file, under dependencies section
        implementation 'io.github.sportstalk247.sdk-multiplatform:core:X.Y.Z'

    .. code-tab:: kotlin Android(Kotlin DSL)
        
        // 1. Add the following in root  **build.gradle.kts** file, repositories section
        dependencyResolutionManagement {
            repositories {
                // ...
                google()
                mavenCentral()
                maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                // ...
            }
        }
        
        // 2. Add the following lines in your module **build.gradle** file, under dependencies section
        implementation("io.github.sportstalk247.sdk-multiplatform:core:X.Y.Z")

    .. code-tab:: ruby iOS(Cocoapods)

        # Under Podfile, Specify Cocoapods version
        pod 'Sportstalk247Kit', '~> X.Y.Z'
        
        # A library to use Kotlin Coroutines and Flow on Swift code in KMP apps.
        # - https://github.com/rickclephas/KMP-NativeCoroutines 
        pod 'KMPNativeCoroutinesAsync', '<version>'    # Swift 5.5 Async/Await implementation
        pod 'KMPNativeCoroutinesCore', '<version>'    # Swift 5.5 Async/Await implementation
        
    .. code-tab:: swift iOS(SPM)
        
        // Under package.swift
        dependencies: [
            .package(url: "https://github.com/sportstalk247/sdk-multiplatform.git", from: "<version>")
        ]
        
        // A library to use Kotlin Coroutines and Flow on Swift code in KMP apps.
        // - https://github.com/rickclephas/KMP-NativeCoroutines
        dependencies: [
            .package(url: "https://github.com/rickclephas/KMP-NativeCoroutines.git", from: "<version>")
        ]
```

![Maven Central](https://img.shields.io/maven-central/v/io.github.sportstalk247.sdk-multiplatform/core?label=Maven%20Central)

Then sync again. The gradle build should now be successful.
