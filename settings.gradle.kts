pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "1.9.23"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

fun includeModule(moduleName: String) {
    val possibleModuleNames = listOf("api", "impl", "model", "widget")
    val moduleDir = File(rootDir, moduleName.replace(":", "/"))
    moduleDir.listFiles()?.forEach {
        if (it.name in possibleModuleNames) {
            include("$moduleName:${it.name}")
        }
    }
}

rootProject.name = "Messenger"
include(":app")

include(":core:common")
include(":data:core:common")

include(":domain:core:common")
includeModule(":domain:auth")


include(":ui:core:common")
include(":ui:core:mvi")
include(":ui:core:navigation")

includeModule(":ui:auth")
includeModule(":ui:splash")




include(":ui:auth:api")
include(":ui:mainpage:api")
include(":ui:mainpage:impl")
include(":data:user:impl")
include(":data:user:api")
include(":domain:user:model")
include(":ui:chat:api")
include(":ui:chat:impl")
include(":domain:user:impl")
include(":domain:user:api")
include(":data:auth:api")
include(":data:auth:impl")
include(":domain:auth:api")
include(":domain:auth:impl")
