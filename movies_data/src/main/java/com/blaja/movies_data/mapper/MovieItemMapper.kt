package com.blaja.movies_data.mapper

import com.blaja.core.mapper.ItemMapper
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.model.Movie

class MovieItemMapper : ItemMapper<Movie, MovieItem> {

    override fun mapToPresentation(model: Movie): MovieItem {
        return MovieItem(
            id = model.id,
            title = model.title,
            adult = model.adult,
            backdropPath = model.backdropPath.let { BACKDROP_BASE_URL + it },
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath.let { POSTER_BASE_URL + it },
            releaseDate = model.releaseDate,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    override fun mapToDomain(modelItem: MovieItem): Movie {
        return Movie(
            id = modelItem.id,
            title = modelItem.title,
            adult = modelItem.adult,
            backdropPath = modelItem.backdropPath?.removePrefix(BACKDROP_BASE_URL),
            originalLanguage = modelItem.originalLanguage,
            originalTitle = modelItem.originalTitle,
            overview = modelItem.overview,
            popularity = modelItem.popularity,
            posterPath = modelItem.posterPath?.removePrefix(POSTER_BASE_URL),
            releaseDate = modelItem.releaseDate,
            video = modelItem.video,
            voteAverage = modelItem.voteAverage,
            voteCount = modelItem.voteCount
        )
    }

    companion object {
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"
    }
}