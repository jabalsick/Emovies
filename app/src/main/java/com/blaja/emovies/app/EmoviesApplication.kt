package com.blaja.emovies.app

import android.app.Application
import com.blaja.movies_data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EmoviesApplication)
            modules(
                listOf(
                    viewModelModule, repositoriesModule, networkModule, mapperModule, getMoviesUseCaseModule,
                    presentationMapperModule,
                    videoMapperModule,
                    getVideosUseCaseModule,
                    detailViewModelModule,
                    moviesDB)
            )
        }
    }
}