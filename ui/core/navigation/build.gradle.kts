plugins {
    id("module.ui.impl")
}

android {
    namespace = "com.batterb.ui.core.navigation"
}

dependencies {
    implementation(project(":ui:splash:impl"))
    implementation(project(":ui:auth:impl"))
}
