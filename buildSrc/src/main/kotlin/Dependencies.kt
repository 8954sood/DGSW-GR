object Kotlin {
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
}

object Google {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT_ANDROID_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
}

object Compose {
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val UI_COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val MATERIAL_COMPOSE = "androidx.compose.material:material:${Versions.MATERIAL_COMPOSE}"
    const val COMPOSE_HILT = "androidx.hilt:hilt-navigation-compose:${Versions.COMPOSE_HILT}"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
    const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"

    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
    const val UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_VERSION}"
}

object OrbitMVI {
    const val ORBIT_CORE = "org.orbit-mvi:orbit-core:${Versions.ORBIT}"
    const val ORBIT_VIEWMODEL = "org.orbit-mvi:orbit-viewmodel:${Versions.ORBIT}"
    const val ORBIT_COMPOSE = "org.orbit-mvi:orbit-compose:${Versions.ORBIT}"
    const val ORBIT_TEST = "org.orbit-mvi:orbit-test:${Versions.ORBIT}"
}