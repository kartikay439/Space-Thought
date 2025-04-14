plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Retrofit Core
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter (for JSON parsing)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for Networking
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // OkHttp Logging Interceptor (for debugging API calls)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")


    api(libs.ktor.client.core)
    implementation("io.ktor:ktor-client-okhttp:2.3.6")
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.client.json)
    api(libs.ktor.serialization.kotlinx.json)
    implementation("io.ktor:ktor-client-logging:2.3.4")

// Koin Core
    api( "io.insert-koin:koin-core:3.5.0")

// Koin Android (for Android-specific functionality)
    api("io.insert-koin:koin-android:3.5.0")

// Koin Android Compatibility (for Jetpack compatibility)
    api ("io.insert-koin:koin-android-compat:3.5.0")
    // For Jetpack WorkManager integration
    api( "io.insert-koin:koin-androidx-workmanager:3.5.0")

// For Navigation Component integration
    api( "io.insert-koin:koin-androidx-navigation:3.5.0")

// For Jetpack Compose integration
    api( "io.insert-koin:koin-androidx-compose:3.5.0")

    //Encrypted shared preference
    implementation("androidx.security:security-crypto:1.0.0-rc02")


    implementation(project(":business"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}