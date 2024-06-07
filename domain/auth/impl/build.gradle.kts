plugins {
    id("module.domain.impl")
}
dependencies {
    implementation(project(":data:auth:api"))
    implementation(project(":domain:auth:api"))
    implementation(project(":data:core:common"))
    implementation(project(":domain:user:model"))
}
