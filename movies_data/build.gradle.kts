plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
    id(GradlePlugins.kotlinParcelize)
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
    implementation(Room.runtime)
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)
    implementation(Koin.navigation)

    implementation(Retrofit2.retrofit)
    implementation(Retrofit2.gsonConverter)
    implementation(Retrofit2.okLogging)
    implementation(Retrofit2.gson)

    implementation(Coroutines.kotlinCoroutines)
    implementation(Coroutines.coroutinesAdapter)

    implementation(AndroidX.lifecycleLiveData)
    implementation(AndroidX.lifecycleViewModel)

    implementation("androidx.annotation:annotation:1.4.0")
    api(project(Modules.core))
    annotationProcessor("androidx.room:room-compiler:${Versions.room}")

    kapt("androidx.room:room-compiler:${Versions.room}")

    androidTestImplementation (AndroidX.testRunner)
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    testImplementation(Mockito.mockitoCore)
    testImplementation(Mockito.mockitoInline)
    testImplementation(Mockito.mockitoKotlin)

    testImplementation(JUnit.jUnit)
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation(Libs.truth)
}
