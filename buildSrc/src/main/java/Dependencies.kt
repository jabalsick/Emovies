object Versions {

    const val kotlin = "1.5.20"
    const val androidPlugin = "7.0.4"
    const val core = "1.7.0"
    const val appCompat = "1.4.0"
    const val navigation = "2.5.1"
    const val material = "1.6.1"
    const val constraintLayout = "2.1.2"
    const val jUnit = "4.13.2"
    const val jUnitExtension = "1.1.3"
    const val mockk = "1.10.2"
    const val mockito = "3.1.0"
    const val coreAndroidTest = "2.1.0"
    const val espresso = "3.4.0"
    const val androidXTest = "1.4.0"
    const val lifecycle = "2.4.0"
    const val retrofit2 = "2.9.0"
    const val okLogging = "4.8.0"
    const val gson = "2.8.6"
    const val coroutines = "1.5.2"
    const val coroutinesAdapter = "0.9.2"
    const val glide = "4.12.0"
    const val intuit = "1.0.6"
    const val timber = "4.7.1"
    const val koin = "3.2.0"
    const val fragmentVersion = "1.4.0"
    const val dataBinding = "3.1.4"
    const val lottie = "5.2.0"
    const val recyclerView = "1.2.1"
    const val room = "2.4.3"
    const val truth ="0.42"
    const val databinding = "7.2.2"
    const val mockitoKotlin = "2.2.0"
}

object AndroidX {

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigationRunTime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationDynamicFeatureFragment =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val jUnitExtension = "androidx.test.ext:junit:${Versions.jUnitExtension}"
    const val coreAndroidTest = "androidx.arch.core:core-testing:${Versions.coreAndroidTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testRunner = "androidx.test:runner:${Versions.androidXTest}"
    const val testRules = "androidx.test:rules:${Versions.androidXTest}"
    const val databinding = "androidx.databinding:databinding-runtime:${Versions.databinding}"
}

object Android {

    const val minSdk = 21
    const val targetSdk = 31
    const val applicationId = "com.blaja.emovies"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Kotlin {
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlinJvmTarget = "1.8"
}

object Retrofit2 {

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    const val okLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okLogging}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Modules {

    const val app = ":app"
    const val splash_feature = ":splash_feature"
    const val home_feature = ":home_feature"
    const val moviesData = ":movies_data"
    const val core = ":core"
}

object Coroutines {

    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    // Koin Test
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinTestJUnit = "io.insert-koin:koin-test-junit4:${Versions.koin}"

}

object Lottie {
    const val dep = "com.airbnb.android:lottie:${Versions.lottie}"
}

object Glide {

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object JUnit {
    const val jUnit = "junit:junit:${Versions.jUnit}"
}

object Mockk {
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}
object Mockito{
    const val mockitoCore ="org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline ="org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin ="com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
}
object Libs {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}

object GradlePlugins {

    const val android = "android"
    const val androidApplication = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinKapt = "kotlin-kapt"
    //const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    //const val navigationSafeArgsKotlin = "androidx.navigation.safeargs.kotlin"

    const val dynamicFeature = "com.android.dynamic-feature"
}

/*object BuildPlugins {

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val navigationSageArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}*/

object BuildType {

    const val debug = "debug"
    const val release = "release"
    const val minifyRelease = false
    const val proguardRelease = "proguard-rules.pro"
    const val minifyDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object InstrumentationRunner {

    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val customTestRunner = "com.example.cleanarchitectureapp.CustomTestRunner"
}

object ConsumerRules {

    const val pro = "consumer-rules.pro"
}

/*object Repositories {

    const val jitpackIOUrl = "https://jitpack.io"
}*/

object Room{
    const val runtime = "androidx.room:room-runtime:${Versions.room}'"
    const val compiler = "androidx.room:room-compiler:${Versions.room}'"
   // const val roomKtx = "androidx.room:room-ktx:${Versions.room}'"
}

