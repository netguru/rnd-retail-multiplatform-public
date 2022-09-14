import extensions.baseAndroidSetup
import modules.ProjectModules.Common
import modules.ProjectModules.CommonResources
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    id(libs.Resources.Moko.multiplatformPlugin)
}

baseAndroidSetup()

android {
    defaultConfig {
        applicationId = libs.Config.applicationId
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.Compose.compilerVersion
    }

    lint {
        abortOnError = false
        xmlReport = true
        xmlOutput = file("${project.rootDir}/androidApp/build/reports/android-lint.xml")
        htmlReport = false
        textReport = false
        checkDependencies = true
    }
}

dependencies {
    implementation(project(Common.projectName))
    implementation(project(CommonResources.projectName))
    implementation(libs.Compose.ui)
    implementation(libs.Compose.material)
    implementation(libs.Compose.ui_tooling_preview)
    implementation(libs.Compose.activity)
    implementation(libs.AndroidX.core_ktx)
    implementation(libs.AndroidX.lifecycle_runtime_ktx)
    implementation(libs.AndroidX.navigation_compose)
    implementation(libs.AndroidX.splash_screen)
    implementation(libs.Resources.androidSvg)

    implementation(libs.AR.scene)

    implementation(libs.Accompanist.pager)
    implementation(libs.Accompanist.pager_indicator)
    implementation(libs.Accompanist.systemuicontroller)
    implementation(libs.Accompanist.drawerPainter)

    implementation(libs.Coil.compose)

    testImplementation(kotlin("test"))
    testImplementation(libs.Test.coroutinesTest)
    testImplementation(libs.Test.junit)
    testImplementation(libs.Test.mockk)
    testImplementation(libs.Test.mockk_agent_jvm)
    androidTestImplementation(libs.Compose.AndroidTest.ui_junit4)
    debugImplementation(libs.Compose.Debug.tooling)
    debugImplementation(libs.Compose.Debug.test_manifest)
}
