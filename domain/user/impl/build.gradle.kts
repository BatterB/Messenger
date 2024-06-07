plugins {
    id("module.domain.impl")
}
dependencies {
    implementation(project(":data:user:api"))
    implementation(project(":domain:user:api"))
    implementation(project(":domain:user:model"))
}
