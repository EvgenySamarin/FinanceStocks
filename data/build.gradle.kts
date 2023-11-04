import config.BuildConfig
import config.BuildConfig.Modules
import config.Lib

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = BuildConfig.COMPILE_SDK_VERSION
    namespace = Modules.DATA_NAMESPACE
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(BuildConfig.BuildType.DEBUG) {
            isMinifyEnabled = false
        }

        getByName(BuildConfig.BuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlin {
        jvmToolchain(jdkVersion = BuildConfig.JDK_TOOLCHAIN_VERSION)
    }
}

dependencies {
    implementation(project(Modules.PROVIDERS_PATH))
    //keep in mind that you need to prevent adding dependencies into model module, cause api absorb whole them
    api(project(Modules.MODELS_PATH))

    implementation(Lib.Core.coroutinesCore)
    implementation(Lib.Core.coreKotlinExt)
    implementation(Lib.Di.hiltCore)
    kapt(Lib.Di.hiltCompiler)

    testImplementation(Lib.Tests.junit)
}