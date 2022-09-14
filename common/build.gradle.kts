import extensions.*
import modules.ProjectModules.*
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.sourceSets
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

baseAndroidSetup()
multiplatformTestSetup()

kotlin {
    android()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = Common.simpleName
            export(project(CommonDomain.projectName))
            export(project(CommonData.projectName))
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(CommonDomain.projectName))
                api(project(CommonData.projectName))
                implementation(libs.Koin.core)
                implementation(libs.Kotlin.coroutines)
            }
        }
        androidMain {
            dependencies {
                // Define Android specific dependencies
            }
        }
        iosMain {
            dependencies {
                // Define iOS specific dependencies
            }
        }
    }
}
