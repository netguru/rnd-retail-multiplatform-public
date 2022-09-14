pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Retail_Multiplatform"
include(":androidApp")
include(":common")
include(":commonResources")
include(":commonData")
include(":commonDomain")
