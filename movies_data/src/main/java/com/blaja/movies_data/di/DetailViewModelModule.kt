package com.blaja.movies_data.di

import com.blaja.movies_data.presentation.DetailMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailViewModelModule = module {
    viewModel { DetailMovieViewModel(get()) }
}