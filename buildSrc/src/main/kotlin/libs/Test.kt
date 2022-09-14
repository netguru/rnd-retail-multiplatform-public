package libs

object Test {
    private const val mockk_version = "1.12.4"
    const val mockk = "io.mockk:mockk:$mockk_version"
    const val mockk_common = "io.mockk:mockk-common:$mockk_version"
    const val mockk_agent_jvm = "io.mockk:mockk-agent-jvm:$mockk_version"

    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.coroutinesVersion}"
    const val turbine = "app.cash.turbine:turbine:0.7.0"

    const val junit = "junit:junit:4.13.2"
}
