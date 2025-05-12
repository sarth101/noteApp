plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.notesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.notesapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.lifecycle:lifecycle-livedata:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime:2.9.0")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.4.0")

    // Room components
    implementation ("androidx.room:room-common:2.7.1")
    annotationProcessor ("androidx.room:room-compiler:2.7.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}