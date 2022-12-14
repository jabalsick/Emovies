package com.blaja.movies_data.di

import com.blaja.movies_data.BuildConfig
import com.blaja.movies_data.remote.api.MovieApi
import com.blaja.movies_data.util.NetworkConstants.API_KEY
import com.blaja.movies_data.util.NetworkConstants.BASE_URL
import com.blaja.movies_data.util.NetworkConstants.SPANISH
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { get<Retrofit>().create(MovieApi::class.java) }
    single { getHeaderInterceptor() }
    single { createOkHttpClient() }
    single { retrofitBuilder() }
}


private fun getHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->

        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", SPANISH)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            addInterceptor(getHeaderInterceptor())
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            readTimeout(120, TimeUnit.SECONDS)
            connectTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        .build()
}

private fun retrofitBuilder() = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(createOkHttpClient())
    .build()
