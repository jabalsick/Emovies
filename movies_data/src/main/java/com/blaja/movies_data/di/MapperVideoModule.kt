package com.blaja.movies_data.di

import com.blaja.movies_data.mapper.VideoMapper
import org.koin.dsl.module

val videoMapperModule = module {

    single { provideVideoMovieMapper() }
}

fun provideVideoMovieMapper() : VideoMapper = VideoMapper()