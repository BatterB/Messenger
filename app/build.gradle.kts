plugins {
    id("module.app")
}

android {
    namespace = "com.batterb.messenger"
    defaultConfig {
        applicationId = "com.batterb.messenger"
    }
}
dependencies {
    implementation(project(":ui:splash:impl"))
    implementation(project(":ui:auth:impl"))
    implementation(project(":data:user:impl"))
    implementation(project(":data:user:api"))
    implementation(project(":domain:user:impl"))
    implementation(project(":domain:user:api"))
    implementation(project(":data:auth:impl"))
    implementation(project(":data:auth:api"))
    implementation(project(":domain:auth:api"))
    implementation(project(":domain:auth:impl"))
}

