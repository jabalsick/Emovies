package com.blaja.movies_data.repository

import com.blaja.movies_data.local.MoviesLocalHandler
import com.blaja.movies_data.local.model.MovieLocalMapper.mapMovieLocalEntity
import com.blaja.movies_data.mapper.MovieMapper
import com.blaja.movies_data.mapper.VideoMapper
import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.model.Video
import com.blaja.movies_data.remote.api.MovieApi
import com.blaja.movies_data.util.MovieApiConstants.TOP_RATED_MOVIE
import com.blaja.movies_data.util.MovieApiConstants.UPCOMING_MOVIE

class MoviesRepository(
    private val movieApi: MovieApi, private val movieMapper: MovieMapper,
    private val videoMapper: VideoMapper, private val moviesLocalHandler: MoviesLocalHandler
) : IMoviesRepository {

    private var page: Int = 0
    private var totalPages: Int = 1
    private var isOnline: Boolean = false
    override suspend fun getMoviesAsync(queryType: String?, param: Map<String, Any>?): List<Movie> {
        processMovieRequest(queryType, param)
        when (queryType) {
            UPCOMING_MOVIE -> return moviesLocalHandler.getUpcomingMovies()
            TOP_RATED_MOVIE -> return moviesLocalHandler.getTopRatedMovies()
        }
        return listOf()
    }

    private suspend fun processMovieRequest(queryType: String?, param: Map<String, Any>?) {
        param.let { isOnline = it?.get("queryOnline") as Boolean }
        if (isOnline) {
            updateLocalMovies(queryType)
        }
    }

    private suspend fun updateLocalMovies(queryType: String?) {
        val response = getRemoteMovies(queryType)
        response.forEach {
            moviesLocalHandler.save(
                mapMovieLocalEntity(
                    it,
                    queryType ?: UPCOMING_MOVIE
                )
            )
        }
    }

    private suspend fun getRemoteMovies(queryType: String?): List<Movie> {
        if (page < totalPages) {
            page += 1
            val response = when (queryType) {
                UPCOMING_MOVIE -> {
                    movieApi.getUpcomingMoviesAsync(page)
                }
                TOP_RATED_MOVIE -> {
                    movieApi.getTopRatedMoviesAsync(page)
                }
                else -> return listOf()
            }

            response.totalPages?.let { totalPages = it }
            return response.movies.map {
                movieMapper.mapToDomain(it)
            }
        }
        return listOf()
    }


    override suspend fun getVideosAsync(id: String): List<Video> {
        val response = id.toInt().let { movieApi.getMovieVideos(MOVIE_TYPE, it) }
        return response.results.map {
            videoMapper.mapToDomain(it)
        }
    }

    companion object {
        const val MOVIE_TYPE = "movie"
    }

}