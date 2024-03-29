package config

object Lib {
    object Version {
        const val kotlin = "1.9.0"

        const val coroutines = "1.7.3"
        const val coreKtx = "1.12.0"

        const val gson = "2.10.1"
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.11.0"

        const val junit = "4.13.2"
        const val runner = "1.1.0"

        const val hilt = "2.48"
        const val hiltComposeNavigation = "1.1.0"

        const val viewMaterial = "1.10.0"
        const val fragmentKtx = "1.6.2"

        const val composeActivity = "1.8.0"
        const val composeLifecycleRuntime = "2.6.2"
        const val composeNavigation = "2.7.5"
        const val bomCompose = "2023.08.00"
    }


    object Di {
        const val hiltComposeNavigation =
            "androidx.hilt:hilt-navigation-compose:${Version.hiltComposeNavigation}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCore = "com.google.dagger:hilt-core:${Version.hilt}"
    }


    object Core {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
        const val coreKotlinExt = "androidx.core:core-ktx:${Version.coreKtx}"

        const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
        const val composeLifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.composeLifecycleRuntime}"
        const val composeNavigation =
            "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    }

    object LegacyUI {
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"
        const val viewMaterial = "com.google.android.material:material:${Version.viewMaterial}"
    }


    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val okHttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Version.okHttpLoggingInterceptor}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    }


    object Tests {
        const val junit = "junit:junit:${Version.junit}"
    }


    object BomCompose {
        const val bomCompose = "androidx.compose:compose-bom:${Version.bomCompose}"
        const val ui = "androidx.compose.ui:ui"
        const val uiViewBinding = "androidx.compose.ui:ui-viewbinding"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"
        const val materialWindowSize = "androidx.compose.material3:material3-window-size-class"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
    }
}