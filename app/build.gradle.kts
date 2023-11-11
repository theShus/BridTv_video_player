plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.bridtv_video_player"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bridtv_video_player"
        minSdk = 32
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.emoji2:emoji2-views-helper:1.3.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")


    // Splash
    implementation ("androidx.core:core-splashscreen:1.0.0-alpha02")

    // Utility
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // Rx
    implementation ("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.0")

    //Compose
//    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.4")
//    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.4")

    //Koin
    val koinVersion = "3.5.0"
    implementation ("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-android:$koinVersion")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:${retrofitVersion}")

    // okHTTP
    val okHttpVersion = "4.9.0"
    implementation ("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
    implementation ("com.squareup.okhttp3:okhttp-urlconnection:$okHttpVersion")

    //Gson
    implementation( "com.squareup.retrofit2:retrofit:2.9.0")
    implementation( "com.squareup.retrofit2:converter-gson:2.9.0")
    implementation( "com.google.code.gson:gson:2.8.8")

    //Exo player
    implementation ("com.google.android.exoplayer:exoplayer:2.18.1")
    implementation ("com.google.android.exoplayer:exoplayer-core:2.16.0")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.16.0")

    //Youtube URL extractor
    implementation ("com.github.HaarigerHarald:android-youtubeExtractor:v2.1.0")
    implementation ("com.github.maxrave-dev:kotlin-youtubeExtractor:0.0.7")

    //Vimeo Network
    implementation ("com.github.vimeo.vimeo-networking-java:vimeo-networking:3.12.0")
    implementation ("com.github.vimeo.vimeo-networking-java:models:3.12.0")

    //Picaso - image setting
    implementation ("com.squareup.picasso:picasso:2.71828")

}