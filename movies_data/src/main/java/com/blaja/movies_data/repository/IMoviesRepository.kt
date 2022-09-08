package com.blaja.movies_data.repository

import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.model.Video

interface IMoviesRepository : Repository {

    suspend fun getMoviesAsync(queryType: String?, param: Map<String, Any>?): List<Movie>

    suspend fun getVideosAsync(id: String): List<Video>
}