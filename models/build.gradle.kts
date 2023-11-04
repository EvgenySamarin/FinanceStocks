import config.BuildConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = BuildConfig.Modules.MODELS_NAMESPACE
    compileSdk = BuildConfig.COMPILE_SDK_VERSION
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