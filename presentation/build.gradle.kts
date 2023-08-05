import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.androidApplication)
    id(Plugins.daggerPlugin)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
    id(Plugins.kt_lint) version Versions.KT_LINT
}

android {
    namespace = ProjectProperties.APPLIACATION_ID
    compileSdk = ProjectProperties.COMPLIDE_SDK_VERSION

    defaultConfig {
        applicationId = ProjectProperties.APPLIACATION_ID
        minSdk = ProjectProperties.MINSDK_VERSION
        targetSdk = ProjectProperties.TAGETSDK_VERSION
        versionCode = ProjectProperties.VERSION_CODE
        versionName = ProjectProperties.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.TEST_RUNER
        vectorDrawables {
            useSupportLibrary = ProjectProperties.USE_SUPPORT_LIBRARY
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = ProjectProperties.IS_MINIFY_ENABLED
            proguardFiles(getDefaultProguardFile(ProjectProperties.PROGUARD_NAME), ProjectProperties.PROGUARD_FILE)
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.JVM_TARGET
    }
    buildFeatures {
        compose = ProjectProperties.BUILD_FEATURE_COMPOSE
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)

    // Compose
    implementation(Compose.ACTIVITY_COMPOSE)
    implementation(Compose.UI_COMPOSE)
    implementation(Compose.UI_TOOLING_PREVIEW)
    implementation(Compose.MATERIAL_COMPOSE)
    implementation(Compose.COMPOSE_HILT)
    implementation(Compose.NAVIGATION_COMPOSE)
    implementation(Compose.MATERIAL3)
    androidTestImplementation(Compose.UI_TEST_JUNIT4)
    debugImplementation(Compose.UI_TOOLING)
    debugImplementation(Compose.UI_TEST_MANIFEST)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // lottie
    implementation(Libraries.LOTTIE)

    // orbit mvi
    implementation(OrbitMVI.ORBIT_CORE)
    implementation(OrbitMVI.ORBIT_COMPOSE)
    implementation(OrbitMVI.ORBIT_VIEWMODEL)
    testImplementation(OrbitMVI.ORBIT_TEST)

    implementation(project(ProjectProperties.PATH_DOMAIN))
    implementation(project(ProjectProperties.PATH_DI))
    implementation(project(ProjectProperties.PATH_REMOTE))
    implementation(project(ProjectProperties.PATH_DATA))

//    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
//    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
//    implementation("androidx.activity:activity-compose:1.5.1")
//    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
//    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
//    implementation("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
//    implementation("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
}