package com.blaja.movies_data.di

import com.blaja.movies_data.mapper.MovieMapper
import org.koin.dsl.module

val mapperModule = module {

    single { provideMovieEntityMapper() }
}

fun provideMovieEntityMapper() : MovieMapper = MovieMapper()