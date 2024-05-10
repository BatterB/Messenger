pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
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
include(":ui:core:mvi")
include(":data:core:common")
include(":domain:core:common")
