package com.blaja.movies_data.local

import com.blaja.movies_data.local.model.MovieLocalMapper.mapMovieList
import com.blaja.movies_data.local.model.MovieLocalEntity
import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.util.MovieApiConstants

class MoviesLocalHandler(private val movieDao: MoviesDao) {

    suspend fun getUpcomingMovies(): List<Movie>{
        return movieDao.getAllMovies().filter { it.category == MovieApiConstants.UPCOMING_MOVIE }.mapMovieList()
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return movieDao.getAllMovies().filter { it.category == MovieApiConstants.TOP_RATED_MOVIE }.mapMovieList()
    }

    suspend fun save(movie: MovieLocalEntity){
        movieDao.saveMovie(movie)
    }
}