
// Top-level build file where you can add configuration options common to all sub-projects/modules.
// TODO: ready: convert stuff in here to version catalog entries
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    kotlin("kapt") version "2.0.0"
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.23" apply false
    id("androidx.room") version "2.6.1" apply false
}