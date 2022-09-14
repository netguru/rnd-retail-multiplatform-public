import extensions.baseAndroidSetup
import extensions.commonMain
import extensions.multiplatformTestSetup

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
            baseName = modules.ProjectModules.CommonData.simpleName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(modules.ProjectModules.CommonDomain.projectName))
                implementation(libs.Kotlin.coroutines)
                implementation(libs.Koin.core)
                implementation(libs.Kotlin.klock)
                implementation(libs.Preferences.multiplatform)
            }
        }
    }
}
