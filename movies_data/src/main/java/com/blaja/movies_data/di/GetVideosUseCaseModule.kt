package com.blaja.movies_data.di

import com.blaja.movies_data.domain.usecase.GetVideosUseCase
import com.blaja.movies_data.repository.IMoviesRepository
import org.koin.dsl.module

val getVideosUseCaseModule = module {
    single { provideGetVideosUseCase(get()) }
}

fun provideGetVideosUseCase(moviesRepository: IMoviesRepository) : GetVideosUseCase = GetVideosUseCase(moviesRepository)

