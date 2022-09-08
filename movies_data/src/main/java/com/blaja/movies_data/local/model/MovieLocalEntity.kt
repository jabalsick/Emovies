package com.blaja.movies_data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blaja.movies_data.model.Movie


@Entity
data class MovieLocalEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "category")
    val category: String
)

fun MovieLocalEntity.mapMovie(): Movie = Movie(
    id = this.id,
    title = this.title,
    adult = this.adult,
    backdropPath = this.backdrop_path,
    originalLanguage = this.original_language,
    originalTitle = this.original_title,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.poster_path,
    releaseDate = this.release_date,
    video = this.video,
    voteAverage = this.vote_average,
    voteCount = this.vote_count
)






