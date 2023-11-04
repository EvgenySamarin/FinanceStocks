// We can't use the BuildConfig file from buildSrc because it's compile after the settings.gradle.kts
// See https://docs.gradle.org/current/userguide/upgrading_version_5.html#classes_from_buildsrc_are_no_longer_visible_to_settings_scripts
// So sad, we need to use code duplication

pluginManagement {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FinanceStocks"
include(":app")
include(":domain")
include(":data")
include(":providers")
include(":models")
