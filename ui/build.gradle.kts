plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.hpgallgery.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.ui.graphics)
    implementation(libs.material3)
    implementation(libs.coil.compose)
    implementation(libs.ui.tooling)
    implementation(libs.ui.tooling.preview)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
