package libs

object Resources {

    const val androidSvg = "com.caverock:androidsvg-aar:1.4"

    object Moko {
        private const val version = "0.20.1"

        const val resourcesGenerator = "dev.icerock.moko:resources-generator:$version"
        const val multiplatformPlugin = "dev.icerock.mobile.multiplatform-resources"
        const val commonResources = "dev.icerock.moko:resources:$version"
        const val androidResources = "dev.icerock.moko:resources-compose:$version"
        const val graphics = "dev.icerock.moko:graphics:0.9.0"
    }
}
