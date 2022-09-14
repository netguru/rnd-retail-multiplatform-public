package extensions

import org.gradle.api.Project

fun Project.multiplatformTestSetup() {
    kotlin {
        sourceSets {
            commonTest {
                dependencies {
                    implementation(kotlin("test"))
                    implementation(libs.Test.coroutinesTest)
                    implementation(libs.Test.mockk_common)
                    implementation(libs.Test.turbine)
                }
            }
            androidTest {
                dependencies {
                    implementation(libs.Test.junit)
                    implementation(libs.Test.mockk)
                    implementation(libs.Test.mockk_agent_jvm)
                }
            }
            iosTest {
                dependencies {
                    // Define repeating ios test libraries
                }
            }
        }
    }
}
