package com.blaja.movies_data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blaja.movies_data.local.model.MovieLocalEntity


@Dao
interface MoviesDao {

    @Query("SELECT * FROM movielocalentity")
    suspend fun  getAllMovies(): List<MovieLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieLocalEntity)
}