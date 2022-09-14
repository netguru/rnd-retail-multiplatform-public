package extensions

import libs.Config
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

fun Project.baseAndroidSetup() {
    android {
        if (project.plugins.hasPlugin("com.android.library")) {
            sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }

        compileSdkVersion(Config.compileSdk)
        defaultConfig {
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk
            versionCode = Config.versionCode
            versionName = Config.versionName
            testInstrumentationRunner = Config.testInstrumentationRunner
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        packagingOptions {
            resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
