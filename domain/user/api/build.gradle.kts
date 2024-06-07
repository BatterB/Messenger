plugins {
    id("module.domain.api")
}
dependencies {
    api(project(":domain:user:model"))
}
