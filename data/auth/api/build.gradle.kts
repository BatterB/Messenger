plugins {
    id("module.data.api")
}
dependencies {
    implementation(project(":data:core:common"))
    implementation(project(":domain:user:model"))
}
