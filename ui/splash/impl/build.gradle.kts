plugins {
    id("module.ui.impl")
}

android{
    namespace = "com.batterb.ui.splash.impl"
}
dependencies {
    implementation(project(":ui:auth:api"))
    implementation(project(":ui:mainpage:api"))
    implementation(project(":domain:user:api"))
}
