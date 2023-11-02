package config

object Lib {
    object Version {
        const val kotlin = "1.9.0"

        const val coroutinesAndroid = "1.7.3"
        const val gson = "2.10.1"
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.11.0"
        const val coreKtx = "1.12.0"

        /* test */
        const val junit = "4.13.2"
        const val runner = "1.1.0"

        const val hilt = "2.48"
        const val hiltComposeNavigation = "1.1.0"

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
    }


    object Core {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
        const val coreCtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val okHttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Version.okHttpLoggingInterceptor}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"

        const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
        const val composeLifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.composeLifecycleRuntime}"
        const val composeNavigation =
            "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    }


    object Tests {
        const val junit = "junit:junit:${Version.junit}"
    }


    object BomCompose {
        const val bomCompose = "androidx.compose:compose-bom:${Version.bomCompose}"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"
        const val materialWindowSize = "androidx.compose.material3:material3-window-size-class"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
    }
}