import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

internal val Project.libs get() = the<LibrariesForLibs>()

dependencies {
    implementation(libs.bundles.common)
    implementation(project(":core:common"))
}