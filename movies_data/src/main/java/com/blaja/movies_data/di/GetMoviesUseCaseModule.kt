package com.blaja.movies_data.di

import com.blaja.movies_data.domain.usecase.GetMoviesUseCase
import com.blaja.movies_data.repository.IMoviesRepository
import org.koin.dsl.module

val getMoviesUseCaseModule = module {
    single { provideGetMoviesUseCase(get()) }
}

fun provideGetMoviesUseCase(moviesRepository: IMoviesRepository) : GetMoviesUseCase = GetMoviesUseCase(moviesRepository)

