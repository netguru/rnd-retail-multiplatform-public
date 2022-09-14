plugins { }

buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.Resources.Moko.resourcesGenerator)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = libs.Kotlin.jvmTarget
            freeCompilerArgs = freeCompilerArgs + libs.Kotlin.optInFlags
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
