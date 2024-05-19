plugins {
    id("module.ui.core")
}

android {
    namespace = "com.batterb.ui.core.mvi"
}

dependencies {
    implementation(project(":ui:core:common"))
}