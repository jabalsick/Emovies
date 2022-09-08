package com.blaja.movies_data.presentation

import com.blaja.movies_data.model.MovieItem

sealed class RecommendedMoviesStateUI {
    object Default : RecommendedMoviesStateUI()
    object Loading : RecommendedMoviesStateUI()
    data class Success(val movies: List<MovieItem>?) : RecommendedMoviesStateUI()
    data class Error(val error: String?) : RecommendedMoviesStateUI()
}