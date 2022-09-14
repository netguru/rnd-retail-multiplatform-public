package libs

object Compose {
    const val version = "1.2.1"
    const val compilerVersion = "1.3.0"

    const val ui = "androidx.compose.ui:ui:$version"
    const val material = "androidx.compose.material:material:$version"
    const val ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:$version"
    const val activity = "androidx.activity:activity-compose:1.4.0"

    object Debug {
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val test_manifest = "androidx.compose.ui:ui-test-manifest:$version"
    }

    object AndroidTest {
        const val ui_junit4 = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object ImageLoading {
        const val coil = "io.coil-kt:coil-compose:2.1.0"
    }
}
