package com.blaja.movies_data.presentation

import com.blaja.movies_data.model.MovieItem

sealed class UpcomingMoviesUIState {
    object Default : UpcomingMoviesUIState()
    object Loading : UpcomingMoviesUIState()
    data class Success(val movies: List<MovieItem>?) : UpcomingMoviesUIState()
    data class Error(val error: String?) : UpcomingMoviesUIState()
}