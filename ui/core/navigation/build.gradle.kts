plugins {
    id("module.ui.impl")
}

android {
    namespace = "com.batterb.ui.core.navigation"
}

dependencies {
    implementation(project(":ui:splash:impl"))
    implementation(project(":ui:auth:impl"))
    implementation(project(":ui:auth:api"))
    implementation(project(":ui:mainpage:api"))
    implementation(project(":ui:mainpage:impl"))
    implementation(project(":ui:chat:api"))
    implementation(project(":ui:chat:impl"))
}
