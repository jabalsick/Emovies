package com.blaja.movies_data.domain.usecase

import com.blaja.core.domain.Resource
import com.blaja.core.domain.UseCase
import com.blaja.movies_data.model.Movie
import com.blaja.movies_data.repository.IMoviesRepository


open class GetMoviesUseCase(
    private val moviesRepository: IMoviesRepository
) : UseCase.ResourceUseCase<List<Movie>> {

    override suspend fun executeAsync(param: Map<String, Any>?,queryType: String?): Resource<List<Movie>> {
        return try {
            val movies = moviesRepository.getMoviesAsync(queryType,param)
            if (movies.isEmpty()) return Resource.success(listOf())
            Resource.success(movies)
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }
}