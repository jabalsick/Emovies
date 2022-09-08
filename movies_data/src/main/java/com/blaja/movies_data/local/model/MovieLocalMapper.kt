package com.blaja.movies_data.local.model

import com.blaja.movies_data.model.Movie

object MovieLocalMapper {

    fun List<MovieLocalEntity>.mapMovieList(): List<Movie> {
        val list = mutableListOf<Movie>()
        this.forEach {
            list.add(it.mapMovie())
        }
        return list
    }

    fun mapMovieLocalEntity(movie: Movie, category: String): MovieLocalEntity =
        MovieLocalEntity(
            id = movie.id,
            title = movie.title ?: "",
            adult = movie.adult ?: false,
            backdrop_path = movie.backdropPath ?: "",
            original_language = movie.originalLanguage ?: "",
            original_title = movie.originalTitle ?: "",
            overview = movie.overview ?: "",
            popularity = movie.popularity ?: 0.0,
            poster_path = movie.posterPath ?: "",
            release_date = movie.releaseDate ?: "",
            video = movie.video ?: false,
            vote_average = movie.voteAverage ?: 0.0,
            vote_count = movie.voteCount ?: 0,
            category = category
        )

}