import config.BuildConfig
import config.Lib

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = BuildConfig.COMPILE_SDK_VERSION
    namespace = BuildConfig.Modules.DOMAIN_NAMESPACE
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
    implementation(project(BuildConfig.Modules.DATA_PATH))

    implementation(Lib.Di.hiltAndroid)
    kapt(Lib.Di.hiltCompiler)

    testImplementation(Lib.Tests.junit)
}