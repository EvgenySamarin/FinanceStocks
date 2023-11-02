package config

object BuildConfig {
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 34
    const val TARGET_SDK_VERSION = 34
    const val BUILD_TOOLS_VERSION = "34.0.0"
    const val COMPOSE_COMPILER_VERSION = "1.5.1"
    const val JDK_TOOLCHAIN_VERSION = 17

    // modules namespaces
    const val APP = "ey.samarin.financestocks"

    object App {
        const val ID = "ey.samarin.financestocks"
        const val NAME = "FinanceStocks"
        const val VERSION_CODE = 1

        /**
         * @return number of version according follow format:
         * 1. digit - major version
         * 2. digit - minor version
         * 3. digit - build number - calculated in build time by app/build.gradle.kts task,
         * see **getCurrentGitBranchName()** и **getGitCommitsCount**
         * 4. letter - represent the environment, calculated in build time by app/build.gradle.kts:
         * - p - production
         * - d - debug
         *
         * Example
         * ```kotlin
         *  1.2.127d
         * ```
         */
        const val VERSION_NAME = "0.1"
    }

    object BuildType {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }
}
