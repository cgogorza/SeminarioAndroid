plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "ar.edu.unicen.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "ar.edu.unicen.movieapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
/////////////////////////
    // Obtiene la API Key desde las propiedades del proyecto
    val tmdbApiKey ="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxODQ1MmFkN2FiZmIzZDMwMzE0YjFkNWM4MWE4MTYzYSIsIm5iZiI6MTczMDA3MDI0OC40MTQ5ODEsInN1YiI6IjY3MGFjYWJjYjE1ZDk3YjFhOTNjMjk4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pWD5k3H1weepjiJX9eFDOGyoZz-NsIG524w0NWlEECA"
    ////////////////
    // Imprime el valor de la API Key para confirmar que se está leyendo correctamente
    println("TMDB_API_KEY from local.properties: $tmdbApiKey")

    buildTypes {
        debug {
            buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
        }
        release {
            buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activityKtx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.glide)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
