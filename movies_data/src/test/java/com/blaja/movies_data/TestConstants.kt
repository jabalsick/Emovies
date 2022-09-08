package com.blaja.movies_data

import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.model.MovieItem

object TestConstants {


    val MOVIE_ITEM_LIST by lazy { listOf(ENGLISH_MOVIE_ITEM, SPANISH_MOVIE_ITEM, JAPANESE_MOVIE_ITEM) }

    val MULTIPLE_LANGUAGES_MOVIE_ITEM_LIST by lazy {
        listOf(
            ENGLISH_MOVIE_ITEM, SPANISH_MOVIE_ITEM, JAPANESE_MOVIE_ITEM,
            SPANISH_MOVIE_ITEM, SPANISH_MOVIE_ITEM, ENGLISH_MOVIE_ITEM,
            JAPANESE_MOVIE_ITEM, ENGLISH_MOVIE_ITEM, SPANISH_MOVIE_ITEM
        )
    }


    private val SPANISH_MOVIE_ITEM = MovieItem(
        adult = false,
        backdropPath = "/lQAe1hfWYDdYypRVdzTbdg6JYWP.jpg",
        id = 901,
        originalLanguage = "es",
        originalTitle = "Contratiempo",
        overview = "Una adolescente profundamente afligida por haber perdido a sus padres comienza a tener un vínculo muy especial con un caballo que ha sido alejado de su familia.",
        popularity = 10.963,
        posterPath = "/hmeP0Fd7TipMllhOdFeSimrLTCs.jpg",
        releaseDate = "2020-11-27",
        title = "Contratiempo",
        video = false,
        voteAverage = 8.2,
        voteCount = 272
    )

    private val ENGLISH_MOVIE_ITEM = MovieItem(
        adult = false,
        backdropPath = "/lQAe1hfWYDdYypRVdzTbdg6JYWP.jpg",
        id = 901,
        originalLanguage = "en",
        originalTitle = "Contratiempo",
        overview = "Una adolescente profundamente afligida por haber perdido a sus padres comienza a tener un vínculo muy especial con un caballo que ha sido alejado de su familia.",
        popularity = 10.963,
        posterPath = "/hmeP0Fd7TipMllhOdFeSimrLTCs.jpg",
        releaseDate = "2020-11-27",
        title = "Contratiempo",
        video = false,
        voteAverage = 8.2,
        voteCount = 272
    )

    private val JAPANESE_MOVIE_ITEM = MovieItem(
        adult = false,
        backdropPath = "/lQAe1hfWYDdYypRVdzTbdg6JYWP.jpg",
        id = 901,
        originalLanguage = "ja",
        originalTitle = "Contratiempo",
        overview = "Una adolescente profundamente afligida por haber perdido a sus padres comienza a tener un vínculo muy especial con un caballo que ha sido alejado de su familia.",
        popularity = 10.963,
        posterPath = "/hmeP0Fd7TipMllhOdFeSimrLTCs.jpg",
        releaseDate = "2020-11-27",
        title = "Contratiempo",
        video = false,
        voteAverage = 8.2,
        voteCount = 272
    )

    val LANGUAGES = listOf("EN", "ES", "JA")

    const val SPANISH_LANGUAGE = "ES"

    val EMPTY_MOVIE_ITEM_LIST = listOf<MovieItem>()

    private val MOVIE = Movie(
        adult = false,
        backdropPath = "/lQAe1hfWYDdYypRVdzTbdg6JYWP.jpg",
        id = 901,
        originalLanguage = "es",
        originalTitle = "Contratiempo",
        overview = "Una adolescente profundamente afligida por haber perdido a sus padres comienza a tener un vínculo muy especial con un caballo que ha sido alejado de su familia.",
        popularity = 10.963,
        posterPath = "/hmeP0Fd7TipMllhOdFeSimrLTCs.jpg",
        releaseDate = "2020-11-27",
        title = "Contratiempo",
        video = false,
        voteAverage = 8.2,
        voteCount = 272
    )

 const val ERROR_MESSAGE = "No pudimos obtener las películas"

}