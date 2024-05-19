import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("org.jetbrains.kotlin.jvm")
}

internal val Project.libs get() = the<LibrariesForLibs>()

dependencies {
    implementation(libs.bundles.common)
}