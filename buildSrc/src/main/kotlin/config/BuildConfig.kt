package config

object BuildConfig {
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 34
    const val TARGET_SDK_VERSION = 34
    const val BUILD_TOOLS_VERSION = "34.0.0"
    const val COMPOSE_COMPILER_VERSION = "1.5.1"
    const val JDK_TOOLCHAIN_VERSION = 17

    //For demonstrate prefer approach to switch between different api in different stages of development
    const val BASE_API_URL_TEST = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/"
    const val BASE_API_URL_PROD = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/"

    object App {
        const val ID = "ey.samarin.financestocks"
        const val NAMESPACE = "ey.samarin.financestocks"
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

    object Modules {
        const val DOMAIN_NAMESPACE = "ey.samarin.domain"
        const val DOMAIN_PATH = ":domain"
        const val DATA_NAMESPACE = "ey.samarin.data"
        const val DATA_PATH = ":data"
        const val PROVIDERS_NAMESPACE = "ey.samarin.providers"
        const val PROVIDERS_PATH = ":providers"
        const val MODELS_NAMESPACE = "ey.samarin.models"
        const val MODELS_PATH = ":models"
    }

    object BuildType {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }
}
