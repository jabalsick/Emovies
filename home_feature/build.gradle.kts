plugins {
    id(GradlePlugins.dynamicFeature)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
    id(GradlePlugins.kotlinParcelize)
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
    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)
    implementation(Koin.navigation)
    implementation(Coroutines.kotlinCoroutines)
    implementation(Glide.glide)
    implementation(Glide.glideCompiler)
    implementation(Libs.material)
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")

    // Test
    testImplementation(JUnit.jUnit)
    testImplementation(Coroutines.coroutinesTest)

    testImplementation(Koin.koinTest)
    testImplementation(Koin.koinTestJUnit)

    androidTestImplementation(AndroidX.jUnitExtension)
    androidTestImplementation(AndroidX.testRunner)
    androidTestImplementation(AndroidX.testRules)
}