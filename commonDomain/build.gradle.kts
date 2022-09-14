import extensions.*
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.sourceSets

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

baseAndroidSetup()
multiplatformTestSetup()

kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = modules.ProjectModules.CommonDomain.simpleName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.Kotlin.coroutines)
                implementation(libs.Koin.core)
                implementation(libs.Kotlin.klock)
            }
        }
        androidMain { }
        iosMain { }
    }
}
