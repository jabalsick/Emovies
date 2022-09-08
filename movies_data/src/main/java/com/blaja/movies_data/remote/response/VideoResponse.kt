package com.blaja.movies_data.remote.response

import com.blaja.movies_data.remote.model.VideoResponse
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<VideoResponse>
)