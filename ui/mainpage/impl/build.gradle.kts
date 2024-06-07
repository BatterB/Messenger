plugins {
    id("module.ui.impl")
}

android {
    namespace = "com.batterb.ui.mainpage.impl"
}
dependencies {
    implementation(project(":ui:chat:api"))
    implementation(project(":ui:auth:api"))
}
