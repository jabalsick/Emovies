package com.blaja.movies_data.mapper

import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.remote.model.MovieResponse

class MovieMapper : EntityMapper<Movie, MovieResponse> {

    override fun mapToDomain(entity: MovieResponse): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }

    override fun mapToEntity(model: Movie): MovieResponse {
        return MovieResponse(
            id = model.id,
            title = model.title,
            adult = model.adult,
            backdropPath = model.backdropPath,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }
}