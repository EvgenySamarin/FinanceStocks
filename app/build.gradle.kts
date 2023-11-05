import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import config.BuildConfig
import config.Lib
import org.apache.commons.io.output.ByteArrayOutputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = BuildConfig.App.NAMESPACE
    compileSdk = BuildConfig.COMPILE_SDK_VERSION
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = BuildConfig.App.ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = BuildConfig.App.VERSION_CODE
        versionName = BuildConfig.App.VERSION_NAME + "." + buildLastPartAPKVersion()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    applicationVariants.all {
        val variant = this
        //Make readable built apk file name
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "${BuildConfig.App.NAME}_${variant.versionName}.apk"
                println("Built outputFileName: $outputFileName")
                output.outputFileName = outputFileName
            }
    }

    buildTypes {
        getByName(BuildConfig.BuildType.DEBUG) {
            versionNameSuffix = "d"
            isDebuggable = true
        }

        getByName(BuildConfig.BuildType.RELEASE) {
            versionNameSuffix = "p"
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

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildConfig.COMPOSE_COMPILER_VERSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BuildConfig.Modules.DOMAIN_PATH))
    api(project(BuildConfig.Modules.MODELS_PATH))

    implementation(Lib.Core.coroutinesAndroid)
    implementation(Lib.Core.coreKotlinExt)
    implementation(Lib.Core.composeActivity)
    implementation(Lib.Core.composeLifecycleRuntime)
    implementation(Lib.Core.composeNavigation)

    implementation(Lib.Di.hiltAndroid)
    implementation(Lib.Di.hiltComposeNavigation)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    kapt(Lib.Di.hiltCompiler)

    implementation(platform(Lib.BomCompose.bomCompose))
    implementation(Lib.BomCompose.ui)
    implementation(Lib.BomCompose.uiGraphics)
    implementation(Lib.BomCompose.uiToolingPreview)
    implementation(Lib.BomCompose.uiViewBinding)
    implementation(Lib.BomCompose.material3)
    implementation(Lib.BomCompose.materialWindowSize)

    //highly discouraged uses the same time the material3 and material, added because of requirements
    implementation(Lib.LegacyUI.viewMaterial)
    implementation(Lib.LegacyUI.fragmentKtx)

    testImplementation(Lib.Tests.junit)

    androidTestImplementation(platform(Lib.BomCompose.bomCompose))
    androidTestImplementation(Lib.BomCompose.uiTestJunit4)

    debugImplementation(Lib.BomCompose.uiTooling)
    debugImplementation(Lib.BomCompose.uiTestManifest)
}

/**
 * Used as a unique identifier for builds.
 * The algorithm for calculating the build number is as follows:
 * 1. Counts the number of commits from the start of the project to the last commit in the branch
 * 1. Counts the number of commits from the main development branch to the latest commit in the branch
 * 1. Subtracts the first value from the second and increases it by one.
 *
 * @return the number of git commits from the main development branch (dev) + 1
 *
 * The unit is added because the project uses squash merges. After merging the current branch with dev,
 * the number of commits will increase by exactly one.
 *
 * @since It is not correct to rely on codeVersion to calculate the build number,
 * as the codeVersion field does not cover a number of situations:
 *  - minor fixes, minor improvements, for which a separate codeVersion is not assembled
 *  - human error, such as forgetting to update codeVersion
 *  - the main purpose of codeVersion is to distinguish the code at a higher level,
 * such as when changing the database or making essential changes
 *
 *  Therefore, it was decided to rely on git to calculate the build number.
 */
fun buildLastPartAPKVersion(): Int {
    return getGitCommitsCountFromStartProjectToHEAD() - getGitCommitsCountFromDevToHEAD() + 1
}

/**
 * There is demand a few conditions to CI:
 * Make sure your CI read whole Git commit history into setting.
 *
 * For example GitLab CI read only 20 history positions by default.
 */
fun getGitCommitsCountFromStartProjectToHEAD(): Int = try {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-list", "--count", "HEAD")
        standardOutput = stdout
    }
    Integer.parseInt(stdout.toString("UTF-8").trim())
} catch (ignored: Throwable) {
    1
}


/**
 * Make sure your pass right developer brunch name
 */
fun getGitCommitsCountFromDevToHEAD(developerBrunchName: String = "dev"): Int = try {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-list", "origin/$developerBrunchName..HEAD", "--count")
        standardOutput = stdout
    }
    Integer.parseInt(stdout.toString("UTF-8").trim())
} catch (ignored: Throwable) {
    1
}