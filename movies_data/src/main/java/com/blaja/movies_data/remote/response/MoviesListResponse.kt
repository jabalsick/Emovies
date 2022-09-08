package com.blaja.movies_data.remote.response

import com.blaja.movies_data.remote.model.MovieResponse
import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var movies: List<MovieResponse> = arrayListOf(),
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results" )
    var totalResults : Int? = null
)