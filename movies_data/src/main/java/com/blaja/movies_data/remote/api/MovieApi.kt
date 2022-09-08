package com.blaja.movies_data.remote.api

import com.blaja.movies_data.remote.response.MoviesListResponse
import com.blaja.movies_data.remote.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesAsync(@Query("page") page: Int): MoviesListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMoviesAsync(@Query("page") page: Int): MoviesListResponse

    @GET("{type}/{id}/videos")
    suspend fun getMovieVideos(@Path("type") type: String, @Path("id") id: Int) : VideoResponse

}