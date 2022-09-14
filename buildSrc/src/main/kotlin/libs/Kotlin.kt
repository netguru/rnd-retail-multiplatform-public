package libs

object Kotlin {
    const val version = "1.7.10"

    const val jvmTarget = "11"
    val optInFlags = listOf("-opt-in=kotlin.RequiresOptIn")

    internal const val coroutinesVersion = "1.6.3"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion-native-mt"

    private const val klockVersion = "3.0.0"
    const val klock =
        "com.soywiz.korlibs.klock:klock:$klockVersion"
}
