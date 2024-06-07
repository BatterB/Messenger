plugins {
    id("module.data.impl")
}

android{
    namespace = "data.user.impl"
}

dependencies {
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.datastore)
    implementation(project(":domain:user:model"))
    implementation(project(":data:user:api"))
}
