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

# Publish to Maven using Nexus Publishing

Must provide the following config values under `local.properties`:
```properties
### KeyId, Password, and Signing Key will be used as params for useInMemoryPgpKeys()
signing.keyId=
signing.password=
signing.key=
### OSSRH Username and Password are your Sonatype Account Credentials
ossrhUsername=
ossrhPassword=
```
To create Sonatype account, proceed to https://central.sonatype.com/.
Also, you may need to download [GPG Keychain](https://gpgtools.org/) App to allow your workstation to publish an artifact.
See the Gradle Nexus [publish-plugin](https://github.com/gradle-nexus/publish-plugin) Github Repo for more info.

# Documentation

Full SDK documentation found here: https://sportstalk247-sdk-multiplatform.readthedocs.io/en/latest/

If you want to generate the SDK documentation locally:
```bash
# install Python3
# setup pip3 virtual environment
# pip3 install sphinx-doc==7.4.7
#   - There are dependency issues on sphinx version 8.x, so we'll stick to 7.x for now 
#  
export PYTHON_SITE_PACKAGES=path/to/python/site-packages
# Change directory to `sdk-android-kotlin/docs` 
cd path/to/sdk-android-kotlin/docs
# Execute build
make html
```