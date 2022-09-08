package com.blaja.movies_data.domain.usecase

import com.blaja.core.domain.Resource
import com.blaja.core.domain.UseCase
import com.blaja.movies_data.model.Video
import com.blaja.movies_data.repository.IMoviesRepository


open class GetVideosUseCase(
    private val moviesRepository: IMoviesRepository
) : UseCase.ResourceUseCase<List<Video>> {

    override suspend fun executeAsync(param: Map<String, Any>?, queryType: String?): Resource<List<Video>> {
        return try {
            val videos = moviesRepository.getVideosAsync(queryType!!)
            if (videos.isEmpty()) return Resource.success(listOf())
            Resource.success(videos)
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }

    companion object{
        const val VIDEO_TRAILER_TYPE = "Trailer"
    }
}