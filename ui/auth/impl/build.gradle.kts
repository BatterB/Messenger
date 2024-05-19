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
    implementation(libs.androidx.compose.material)
    implementation(project(":domain:auth:model"))

}

