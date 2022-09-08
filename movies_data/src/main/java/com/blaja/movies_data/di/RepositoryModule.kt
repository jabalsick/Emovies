package com.blaja.movies_data.di

import com.blaja.movies_data.local.MoviesLocalHandler
import com.blaja.movies_data.mapper.MovieMapper
import com.blaja.movies_data.mapper.VideoMapper
import com.blaja.movies_data.remote.api.MovieApi
import com.blaja.movies_data.repository.IMoviesRepository
import com.blaja.movies_data.repository.MoviesRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { provideMoviesRepository(get(), get(), get(),get()) }
}

fun provideMoviesRepository(
    movieApi: MovieApi,
    movieMapper: MovieMapper,
    videoMapper: VideoMapper,
    movieLocalHandler: MoviesLocalHandler
): IMoviesRepository = MoviesRepository(
    movieApi = movieApi,
    movieMapper = movieMapper,
    videoMapper = videoMapper,
    moviesLocalHandler = movieLocalHandler)
