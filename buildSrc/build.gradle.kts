
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("com.android.tools.build:gradle:7.2.2")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")
}
