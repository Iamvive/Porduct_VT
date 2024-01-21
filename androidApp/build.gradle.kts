plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.appwork.porductvt.android"
  compileSdk = 33
  defaultConfig {
    applicationId = "com.appwork.porductvt.android"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.7"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
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
}

dependencies {

  val lifecycleVersion = "2.6.0"
  implementation(project(":shared"))
  implementation("androidx.compose.ui:ui:1.4.3")
  implementation("androidx.compose.ui:ui-tooling:1.4.3")
  implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
  implementation("androidx.compose.foundation:foundation:1.4.3")
  implementation("androidx.compose.material:material:1.4.3")
  implementation("androidx.activity:activity-compose:1.7.1")

  //Hilt
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")

  //Jetpack
  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
  // ViewModel utilities for Compose
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
  // Lifecycle utilities for Compose
  implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
  // Annotation processor
  kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

  //Coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}

kapt {
  correctErrorTypes = true
}
