package com.blaja.movies_data.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blaja.core.domain.Status
import com.blaja.core.presentation.BaseViewModel
import com.blaja.movies_data.domain.usecase.GetVideosUseCase
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.model.Video
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val getVideosUseCase: GetVideosUseCase
) : BaseViewModel() {
    private val detailVideosState = MutableLiveData<VideosRequestUIState>()
    val detailVideosLiveData: LiveData<VideosRequestUIState> get() = detailVideosState

    fun requestVideoList(id: String) {
        viewModelScope.launch {
            detailVideosState.value = VideosRequestUIState.Loading
            requestVideos(id)
        }
    }

    private suspend fun requestVideos(id: String) {
        val resultUpcoming = getVideosUseCase.executeAsync(queryType = id)
        when (resultUpcoming.status) {
            Status.SUCCESS -> {
                detailVideosState.value =
                    VideosRequestUIState.Success(filterOnlyTrailers(resultUpcoming.data))
            }
            Status.ERROR -> detailVideosState.value =
                VideosRequestUIState.Error(resultUpcoming.message)
            Status.LOADING -> detailVideosState.value = VideosRequestUIState.Loading
            else -> {
                detailVideosState.value = VideosRequestUIState.Default
            }
        }
    }

    private fun filterOnlyTrailers(videos: List<Video>?): List<Video> {
        return videos?.filter { video: Video -> video.type == GetVideosUseCase.VIDEO_TRAILER_TYPE } ?: listOf()
    }
}