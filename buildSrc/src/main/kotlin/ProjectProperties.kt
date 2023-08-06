import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    const val NAME_SPACE_DOMAIN = "com.hu.dgswgr.domain"
    const val NAME_SPACE_DATA = "com.hu.dgswgr.data"
    const val NAME_SPACE_DI = "com.hu.dgswgr.di"
    const val NAME_SPACE_REMOTE = "com.hu.dgswgr.remote"
    const val NAME_SPACE_LOCAL = "com.hu.dgswgr.local"

    const val APPLIACATION_ID = "com.hu.dgswgr"
    const val COMPLIDE_SDK_VERSION = 33

    const val MINSDK_VERSION = 26
    const val TAGETSDK_VERSION = 33
    const val TEST_RUNER = "androidx.test.runner.AndroidJUnitRunner"
    const val USE_SUPPORT_LIBRARY = true

    const val IS_MINIFY_ENABLED = true
    const val PROGUARD_NAME = "proguard-android-optimize.txt"
    const val PROGUARD_FILE = "proguard-rules.pro"

    val JAVA_VERSION = JavaVersion.VERSION_17
    val JVM_TARGET = JAVA_VERSION.toString()

    const val BUILD_FEATURE_COMPOSE = true

    const val COMPILE_SDK_VERSION = 33
    const val EXCLUDES = "/META-INF/{AL2.0,LGPL2.1}"

    const val ANDROID_VERSION = "7.4.0"
    const val JETBRAINS_KOTLIN = "1.7.10"


    const val PATH_PRESENTATION = ":presentation"
    const val PATH_DOMAIN = ":domain"
    const val PATH_DATA = ":data"
    const val PATH_DI = ":di"
    const val PATH_REMOTE = ":remote"
    const val PATH_LOCAL = ":local"
}