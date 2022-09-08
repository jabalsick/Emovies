plugins {
    id(GradlePlugins.androidApplication)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = InstrumentationRunner.customTestRunner
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

    buildFeatures {
        viewBinding = true
    }

     dynamicFeatures.addAll(
        setOf(Modules.splash_feature,Modules.home_feature))
}

dependencies {

    // Core
    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(Libs.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.recyclerView)

    // Modules
    api(project(Modules.moviesData))
    api(project(Modules.core))

    // Navigation
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationDynamicFeatureFragment)
    implementation(AndroidX.navigationUi)

    // Timber
    implementation(Libs.timber)

    // Koin
    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)
    implementation(Koin.navigation)

    // Gson Parse
    implementation(Retrofit2.gson)

    api (Lottie.dep)
}