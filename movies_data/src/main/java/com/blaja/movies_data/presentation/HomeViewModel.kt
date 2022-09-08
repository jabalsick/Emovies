package com.blaja.movies_data.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blaja.core.domain.Status
import com.blaja.core.presentation.BaseViewModel
import com.blaja.movies_data.domain.usecase.GetMoviesUseCase
import com.blaja.movies_data.filterRecommendedMoviesByLanguage
import com.blaja.movies_data.getLanguages
import com.blaja.movies_data.limitMoviesQuantity
import com.blaja.movies_data.mapper.MovieItemMapper
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.util.MovieApiConstants.TOP_RATED_MOVIE
import com.blaja.movies_data.util.MovieApiConstants.UPCOMING_MOVIE
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val movieMapper: MovieItemMapper
) : BaseViewModel() {
    private val moviesUpcomingListState = MutableLiveData<UpcomingMoviesUIState>()
    private val moviesTopRatedListState = MutableLiveData<TopRatedMoviesStateUI>()
    val movieItemsLiveData: LiveData<UpcomingMoviesUIState> get() = moviesUpcomingListState
    val movieTopRatedItemsLiveData: LiveData<TopRatedMoviesStateUI> get() = moviesTopRatedListState

    private val moviesRecommendedListState = MutableLiveData<RecommendedMoviesStateUI>()
    val movieRecommendedItemsLiveData: LiveData<RecommendedMoviesStateUI> get() = moviesRecommendedListState

    private var moviesRecommendedList: List<MovieItem>? = listOf()
    val moviesAvailableLanguages: MutableList<String?> = mutableListOf()


    fun getUpcomingMovies(queryOnline: Boolean) {
        viewModelScope.launch {
            setUpcomingState(UpcomingMoviesUIState.Loading)
            val params = mapOf(QUERY_ONLINE_KEY to queryOnline)
            requestUpcomingMovies(params)
        }
    }

    fun getTopRatedMovies(queryOnline: Boolean) {
        viewModelScope.launch {
            setTopRateState(TopRatedMoviesStateUI.Loading)
            setRecommendedState(RecommendedMoviesStateUI.Loading)
            val params = mapOf(QUERY_ONLINE_KEY to queryOnline)
            requestTopRatedMovies(params)
        }
    }

    fun updateRecommendedMovies(movies: List<MovieItem>?) {
        moviesRecommendedListState.value =
            RecommendedMoviesStateUI.Success(movies?.limitMoviesQuantity())
    }

    fun updateAvailableLanguages() {
        moviesRecommendedList?.let {
            setRecommendedMoviesLanguages(it)
        }
    }

    fun filterRecommendedMoviesByLanguage(lang: String) {
        updateRecommendedMovies(moviesRecommendedList?.filterRecommendedMoviesByLanguage(lang))
    }

    private suspend fun requestUpcomingMovies(params: Map<String, Any>) {
        val resultUpcoming = getMoviesUseCase.executeAsync(params, queryType = UPCOMING_MOVIE)
        when (resultUpcoming.status) {
            Status.SUCCESS -> {
                setUpcomingState(UpcomingMoviesUIState.Success(resultUpcoming.data?.map {
                    movieMapper.mapToPresentation(it)
                }))
            }
            Status.ERROR ->
                setUpcomingState(UpcomingMoviesUIState.Error(resultUpcoming.message))
            Status.LOADING -> moviesUpcomingListState.value = UpcomingMoviesUIState.Loading
            else -> {
                setUpcomingState(UpcomingMoviesUIState.Default)
            }
        }
    }

    private suspend fun requestTopRatedMovies(params: Map<String, Any>) {
        val resultTopRated =
            getMoviesUseCase.executeAsync(params, queryType = TOP_RATED_MOVIE)
        when (resultTopRated.status) {
            Status.SUCCESS -> {
                val movies = resultTopRated.data?.map {
                    movieMapper.mapToPresentation(it)
                }
                moviesRecommendedList = movies
                setTopRateState(TopRatedMoviesStateUI.Success(movies))
            }
            Status.ERROR -> {
                setTopRateState(TopRatedMoviesStateUI.Error(resultTopRated.message))
                setRecommendedState(RecommendedMoviesStateUI.Error(resultTopRated.message))

            }
            Status.LOADING -> {
                setTopRateState(TopRatedMoviesStateUI.Loading)
                setRecommendedState(RecommendedMoviesStateUI.Loading)
            }
            else -> {
                setTopRateState(TopRatedMoviesStateUI.Default)
                setRecommendedState(RecommendedMoviesStateUI.Default)
            }
        }
    }

    private fun setRecommendedMoviesLanguages(movies: List<MovieItem>) {
        moviesAvailableLanguages.clear()
        moviesAvailableLanguages.add(ALL_LANG)
        moviesAvailableLanguages.addAll(movies.getLanguages())
    }

    infix fun setUpcomingState(state: UpcomingMoviesUIState) {
        moviesUpcomingListState.value = state
    }

    private fun setTopRateState(state: TopRatedMoviesStateUI) {
        moviesTopRatedListState.value = state
    }

    private fun setRecommendedState(state: RecommendedMoviesStateUI) {
        moviesRecommendedListState.value = state
    }


    companion object {
        const val ALL_LANG = "Todos"
        const val RECOMMENDED_MOVIES_LIMIT = 6
        const val QUERY_ONLINE_KEY = "queryOnline"
    }
}

