plugins {
    id("module.data.impl")
}

android{
    namespace = "com.batterb.data.auth.impl"
}
dependencies {
    implementation(project(":data:auth:api"))
    implementation(project(":core:common"))
    implementation(project(":data:core:common"))
    implementation(project(":domain:user:model"))
}
