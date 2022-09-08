package com.blaja.movies_data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blaja.movies_data.local.model.MovieLocalEntity

@Database(entities = [MovieLocalEntity::class], version = 1)
abstract class MoviesDB : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

     companion object {
        private var INSTANCE: MoviesDB? = null

        fun getMoviesDatabase(context: Context): MoviesDB{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                MoviesDB::class.java,
                "movies_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}