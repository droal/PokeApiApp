plugins {
    id("com.android.application")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace ="com.example.pokeapilulo"
    compileSdk = getIntExtra("compileSdkVersion")

    defaultConfig {
        applicationId = "com.example.pokeapilulo"
        minSdk = getIntExtra("minSdkVersion")
        targetSdk = getIntExtra("targetSdkVersion")
        versionCode = getIntExtra("versionCode")
        versionName = getStringExtra("versionName")

        buildConfigField("String","POKE_API_BASE_URL", rootProject.properties["POKE_API_BASE_URL"] as String)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:${getStringExtra("retrofitVersion")}")
    implementation ("com.squareup.okhttp3:logging-interceptor:${getStringExtra("loggingInterceptorVersion")}")
    implementation ("com.squareup.retrofit2:converter-gson:${getStringExtra("retrofitVersion")}")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:${getStringExtra("retrofitVersion")}")

    //dagger
    implementation ("com.google.dagger:dagger:${getStringExtra("googleDaggerVersion")}")
    kapt ("com.google.dagger:dagger-compiler:${getStringExtra("googleDaggerVersion")}")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:${getStringExtra("navigationVersion")}")
    implementation ("androidx.navigation:navigation-ui-ktx:${getStringExtra("navigationVersion")}")

    //Live data
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:${getStringExtra("lifecycleVersion")}")
    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:${getStringExtra("lifecycleVersion")}")


    //Glide
    implementation ("com.github.bumptech.glide:glide:${getStringExtra("glideVersion")}")
    kapt ("com.github.bumptech.glide:compiler:${getStringExtra("glideVersion")}")

    //GlideToVectorYou
    implementation ("com.github.2coffees1team:GlideToVectorYou:${getStringExtra("glideToVectorVersion")}")
    //implementation 'com.github.corouteam:GlideToVectorYou:v2.0.0'

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:${getStringExtra("testCoroutineVersion")}")
    testImplementation ("org.mockito:mockito-core:${getStringExtra("mockitoVersion")}")
    testImplementation ("io.mockk:mockk:${getStringExtra("mockkVersion")}")
    testImplementation ("androidx.arch.core:core-testing:${getStringExtra("androidxArchVersion")}")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:${getStringExtra("mockitoKotlinVersion")}")
}

fun getIntExtra(name: String) = rootProject.extra[name] as Int
fun getStringExtra(name: String) = rootProject.extra[name] as String