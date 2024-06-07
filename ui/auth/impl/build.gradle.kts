plugins {
    id("module.ui.impl")
}

android{
    namespace = "com.batterb.ui.auth.impl"
}
dependencies{
    implementation(libs.zxing.core)
    implementation(libs.androidx.camera)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.compose.material)
    implementation(project(":domain:auth:model"))
    implementation(project(":ui:mainpage:api"))
    implementation(project(":data:user:impl"))
    implementation(project(":domain:auth:api"))
    implementation(project(":domain:user:api"))
    implementation(project(":data:core:common"))
    implementation(project(":domain:user:model"))

}

