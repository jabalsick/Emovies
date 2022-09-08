package com.blaja.movies_data

import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.presentation.HomeViewModel


fun List<MovieItem>.getLanguages(): List<String?> {
    return sortedBy { it.originalLanguage }.map { movie ->
        movie.originalLanguage?.uppercase()
    }.distinct()
}

fun List<MovieItem>.limitMoviesQuantity(): List<MovieItem> {
    val filtered: List<MovieItem> = this
    return if (size > HomeViewModel.RECOMMENDED_MOVIES_LIMIT) {
        filtered.dropLast(size - HomeViewModel.RECOMMENDED_MOVIES_LIMIT)
    } else {
        filtered
    }
}

fun List<MovieItem>.filterRecommendedMoviesByLanguage(lang: String):List<MovieItem> {
    val filtered: List<MovieItem> = this
    return if (lang == HomeViewModel.ALL_LANG) {
        filtered
    } else {
         return filtered.filter { movie ->
            movie.originalLanguage?.uppercase() == lang
        }
    }
}



