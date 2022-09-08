package com.blaja.movies_data.presentation

import com.blaja.movies_data.model.MovieItem

sealed class TopRatedMoviesStateUI {
    object Default : TopRatedMoviesStateUI()
    object Loading : TopRatedMoviesStateUI()
    data class Success(val movies: List<MovieItem>?) : TopRatedMoviesStateUI()
    data class Error(val error: String?) : TopRatedMoviesStateUI()
}