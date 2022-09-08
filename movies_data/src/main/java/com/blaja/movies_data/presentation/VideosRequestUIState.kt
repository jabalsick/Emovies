package com.blaja.movies_data.presentation

import com.blaja.movies_data.model.Video

sealed class VideosRequestUIState {
    object Default : VideosRequestUIState()
    object Loading : VideosRequestUIState()
    data class Success(val videos: List<Video>) : VideosRequestUIState()
    data class Error(val error: String?) : VideosRequestUIState()
}