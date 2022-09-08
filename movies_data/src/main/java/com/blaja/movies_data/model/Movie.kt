package com.blaja.movies_data.model

import com.blaja.core.model.Model

data class Movie(
    var id: Int,
    var title: String? = null,
    var adult: Boolean? = null,
    var backdropPath: String?  = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
) : Model()