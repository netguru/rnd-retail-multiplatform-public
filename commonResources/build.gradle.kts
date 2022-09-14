import extensions.androidMain
import extensions.baseAndroidSetup
import extensions.commonMain
import modules.ProjectModules.CommonResources
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id(libs.Resources.Moko.multiplatformPlugin)
}

baseAndroidSetup()

kotlin {
    android()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = CommonResources.simpleName
            export(libs.Resources.Moko.commonResources)
            export(libs.Resources.Moko.graphics)
            xcf.add(this)
        }
    }

    cocoapods {
        summary = "This module contains shared resources for the project - colors, fonts, strings"
        ios.deploymentTarget = libs.IOS.deploymentTarget
        version = libs.IOS.cocoapodsVersion
        framework {
            baseName = CommonResources.simpleName
            export(libs.Resources.Moko.commonResources)
            export(libs.Resources.Moko.graphics)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.Resources.Moko.commonResources)
            }
        }
        androidMain {
            dependencies {
                api(libs.Resources.Moko.androidResources)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = CommonResources.packageName
}
