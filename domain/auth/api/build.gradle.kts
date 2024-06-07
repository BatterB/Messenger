plugins {
    id("module.domain.api")
}
dependencies {
    implementation(project(":data:core:common"))
    implementation(project(":domain:user:model"))
}
