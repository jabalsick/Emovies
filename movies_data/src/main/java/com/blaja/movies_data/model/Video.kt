package com.blaja.movies_data.model

import com.blaja.core.model.Model

data class Video(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean? = false,
) : Model()