plugins {
    id(GradlePlugins.dynamicFeature)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
    id(GradlePlugins.navigationSafeArgs)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        minSdk = Android.minSdk
        testInstrumentationRunner = InstrumentationRunner.customTestRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Kotlin.kotlinJvmTarget
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(project(Modules.app))
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
}