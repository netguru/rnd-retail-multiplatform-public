package extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.android(block: BaseExtension.() -> Unit) {
    extensions.getByType<BaseExtension>().block()
}

fun Project.kotlin(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.getByType<KotlinMultiplatformExtension>().block()
}

typealias SourceSets = NamedDomainObjectContainer<KotlinSourceSet>

fun KotlinMultiplatformExtension.sourceSets(block: SourceSets.() -> Unit) {
    sourceSets.block()
}

private fun SourceSets.getOrCreate(name: String): KotlinSourceSet = findByName(name) ?: create(name)

fun SourceSets.commonMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("commonMain").apply(block)

fun SourceSets.commonTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("commonTest").apply(block)

fun SourceSets.androidMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("androidMain").apply(block)

fun SourceSets.androidTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("androidTest").apply(block)

fun SourceSets.iosX64Main(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosX64Main").apply(block)

fun SourceSets.iosX64Test(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosX64Test").apply(block)

fun SourceSets.iosArm64Main(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosArm64Main").apply(block)

fun SourceSets.iosArm64Test(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosArm64Test").apply(block)

fun SourceSets.iosSimulatorArm64Main(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosSimulatorArm64Main").apply(block)

fun SourceSets.iosSimulatorArm64Test(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosSimulatorArm64Test").apply(block)

fun SourceSets.iosMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosMain").apply {
        apply(block)
        iosX64Main().dependsOn(this)
        iosArm64Main().dependsOn(this)
        iosSimulatorArm64Main().dependsOn(this)
    }

fun SourceSets.iosTest(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("iosTest").apply {
        apply(block)
        dependsOn(commonTest())
        iosX64Test().dependsOn(this)
        iosArm64Test().dependsOn(this)
        iosSimulatorArm64Test().dependsOn(this)
    }
