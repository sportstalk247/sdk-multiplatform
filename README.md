![Maven Central](https://img.shields.io/maven-central/v/io.github.sportstalk247.sdk-multiplatform/core?label=Maven%20Central)
[![Documentation Status](https://readthedocs.org/projects/sportstalk247-sdk-multiplatform/badge/?version=latest)](https://sportstalk247-sdk-multiplatform.readthedocs.io/en/latest/?badge=latest)


# Sportstalk247 SDK Multiplatform

First, you need to register SportsTalk API with 'Appkey' and 'Token'.
You need to visit the dashboard with the following URL:

https://dashboard.sportstalk247.com

- Then click on ''Application Management'' link to generate the above

# Setup Gradle Repository

The SportsTalk SDK is published on [Maven Central](https://central.sonatype.com/).

In order to use it in your application, do the following:

1. Add the following in root **build.gradle** file

For Groovy:
```groovy
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
```
For Kotlin DSL:
```kotlin
// ...
dependencyResolutionManagement {
    repositories {
        // ...
        google()
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        // ...
    }
}
// ...
```

2. Add the following lines in your module **build.gradle** file:

For Groovy:
```groovy
implementation 'io.github.sportstalk247.sdk-multiplatform:core:X.Y.Z'
```

For Kotlin DSL:
```groovy
implementation("io.github.sportstalk247.sdk-multiplatform:core:X.Y.Z")
```

![Maven Central](https://img.shields.io/maven-central/v/io.github.sportstalk247.sdk-multiplatform/core?label=Maven%20Central)

Then sync again. The gradle build should now be successful.

# Documentation

Full Android SDK documentation found here: https://sportstalk247-sdk-multiplatform.readthedocs.io/en/latest/
