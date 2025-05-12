// settings.gradle.kts (Project Settings)
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Prevents repositories in other gradle files
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PRLSV6"
include(":app")
