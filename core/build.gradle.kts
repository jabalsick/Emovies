plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

        testInstrumentationRunner = InstrumentationRunner.instrumentationRunner
        consumerProguardFiles(ConsumerRules.pro)
    }

    buildTypes {
        getByName(BuildType.release) {
            isMinifyEnabled = BuildType.minifyRelease
            proguardFiles(BuildType.proguardRelease)
        }

        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFiles(BuildType.proguardDebug)
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Kotlin.kotlinJvmTarget
    }

}


dependencies {
    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(Libs.material)
    implementation(Coroutines.kotlinCoroutines)
    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)
    implementation(Koin.navigation)
    implementation(AndroidX.databinding)
    androidTestImplementation (AndroidX.testRunner)
    testImplementation(Mockito.mockitoCore)
    testImplementation(Mockito.mockitoInline)
    testImplementation(Mockito.mockitoKotlin)

    testImplementation(JUnit.jUnit)
    testImplementation(AndroidX.coreAndroidTest)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation(Libs.truth)

}