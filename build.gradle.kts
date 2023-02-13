// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

buildscript{
    val safeArgsPluginVersion = "2.5.3"
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$safeArgsPluginVersion")
    }
}

ext {
    extra["compileSdkVersion"] = 32
    extra["minSdkVersion"] = 23
    extra["targetSdkVersion"] = 32
    extra["versionCode"] = 1
    extra["versionName"] = "1.0"

    extra["retrofitVersion"] = "2.9.0"
    extra["loggingInterceptorVersion"] = "4.10.0"
    extra["navigationVersion"] = "2.4.2"
    extra["lifecycleVersion"] = "2.5.1"
    extra["googleDaggerVersion"] = "2.44.2"
    extra["glideVersion"] = "4.14.2"
    extra["glideToVectorVersion"] = "v2.0.0"


    extra["testCoroutineVersion"] = "1.6.4"
    extra["mockitoVersion"] = "4.9.0"
    extra["mockkVersion"] = "1.13.3"

}