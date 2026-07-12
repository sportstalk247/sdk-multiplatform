val packageGroup by extra { "io.github.sportstalk247.${rootProject.name}" }
val packageVersion by extra { "2.0.0-beta07" }
val nativeFrameworkName by extra { "Sportstalk247Kit" }

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.googleKsp) apply false

    alias(libs.plugins.mavenPublish) apply false
}

allprojects {
    group = rootProject.extra["packageGroup"].toString()
    version = rootProject.extra["packageVersion"].toString()

    // Publishing to Maven Central via the Sonatype Central Portal is handled by the Vanniktech
    // Maven Publish plugin. It auto-detects the Kotlin Multiplatform / Android Gradle plugins and
    // wires up the publications, sources jar, javadoc jar and PGP signing automatically.
    //
    // Credentials & signing keys are read from Gradle properties — put these in your GLOBAL
    // ~/.gradle/gradle.properties (never commit them):
    //   mavenCentralUsername / mavenCentralPassword                          -> Central Portal user token
    //   signingInMemoryKey / signingInMemoryKeyId / signingInMemoryKeyPassword -> PGP key (gpg --export-secret-keys --armor)
    plugins.withId("com.vanniktech.maven.publish") {
        extensions.configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
            // No-arg overload targets the Central Portal (central.sonatype.com).
            publishToMavenCentral()
            signAllPublications()

            // group + artifactId (= module name "core"/"model") + version are inherited from the
            // project; call coordinates(...) only to override an artifactId.
            pom {
                name.set("Sportstalk247 SDK Multiplatform")
                description.set("A Kotlin Multiplatform library powered by SportsTalk.")
                inceptionYear.set("2024")
                url.set("https://github.com/sportstalk247/sdk-multiplatform")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("dev-lcc")
                        name.set("Lawrence C. Cendaña")
                    }
                }
                scm {
                    url.set("https://github.com/sportstalk247/sdk-multiplatform")
                    connection.set("scm:git:git://github.com/sportstalk247/sdk-multiplatform.git")
                    developerConnection.set("scm:git:ssh://git@github.com/sportstalk247/sdk-multiplatform.git")
                }
            }
        }
    }
}
