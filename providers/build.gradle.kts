import config.BuildConfig
import config.Lib

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = BuildConfig.COMPILE_SDK_VERSION
    namespace = BuildConfig.Modules.PROVIDERS_NAMESPACE
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        all {
            buildConfigField("String", "BASE_API_URL", "\"${BuildConfig.BASE_API_URL_TEST}\"")
        }

        getByName(BuildConfig.BuildType.DEBUG) {
            isMinifyEnabled = false
        }

        getByName(BuildConfig.BuildType.RELEASE) {
            buildConfigField("String", "BASE_API_URL", "\"${BuildConfig.BASE_API_URL_PROD}\"")
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
    implementation(Lib.Network.retrofit)
    implementation(Lib.Network.okHttpLoggingInterceptor)
    implementation(Lib.Network.gson)
    implementation(Lib.Network.gsonConverter)

    implementation(Lib.Di.hiltAndroid)
    kapt(Lib.Di.hiltCompiler)

    testImplementation(Lib.Tests.junit)
}