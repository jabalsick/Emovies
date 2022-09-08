package com.blaja.movies_data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoLocalEntity (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "iso_3166_1") val iso_3166_1: String,
    @ColumnInfo(name = "iso_639_1") val iso_639_1: String,
    @ColumnInfo(name = "key") val key: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "site") val site: String,
    @ColumnInfo(name = "size") val size: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "official") val official: Boolean? = false
)