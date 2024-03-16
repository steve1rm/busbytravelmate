plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.conventionalCommits)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kotlinxParcelize)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "me.androidbox.busbytravelmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.androidbox.busbytravelmate"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = false
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// example: git commit -am"chore: (android) adds conventional commits"
// see: https://github.com/nicolasfara/conventional-commits
conventionalCommits {
    types += listOf()
    scopes += listOf("android")
    failureMessage = "The commit message does not meet the Conventional Commit standard"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":components"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.koin.androidx.compose)
    implementation(libs.timber)
    implementation(libs.koin.android)
    implementation(libs.firebase.auth)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}