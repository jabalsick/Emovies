package com.blaja.movies_data.di


import com.blaja.movies_data.mapper.MovieItemMapper
import org.koin.dsl.module

val presentationMapperModule = module {

    single { provideMovieItemMapper() }
}

fun provideMovieItemMapper() : MovieItemMapper = MovieItemMapper()