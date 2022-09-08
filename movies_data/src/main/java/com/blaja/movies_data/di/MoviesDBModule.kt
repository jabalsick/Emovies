package com.blaja.movies_data.di

import android.content.Context
import androidx.room.Room
import com.blaja.movies_data.local.MoviesDB
import com.blaja.movies_data.local.MoviesDao
import com.blaja.movies_data.local.MoviesLocalHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val moviesDB = module {
    fun provideDataBase(context: Context): MoviesDB {
        return Room.databaseBuilder(context, MoviesDB::class.java, "movies_table")
            .build()
    }

    fun provideDao(dataBase: MoviesDB): MoviesDao {
        return dataBase.moviesDao()
    }

    fun provideMoviesLocalHandler(moviesDao: MoviesDao): MoviesLocalHandler{
        return MoviesLocalHandler(moviesDao)
    }

    single { provideDataBase(androidContext()) }
    single { provideDao(get()) }
    single {provideMoviesLocalHandler(get())}
}