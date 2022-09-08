package com.blaja.movies_data.presentation

import androidx.lifecycle.Observer
import com.blaja.core.domain.Resource
import com.blaja.movies_data.BaseUnitTest
import com.blaja.movies_data.TestConstants.ERROR_MESSAGE
import com.blaja.movies_data.domain.usecase.GetMoviesUseCase
import com.blaja.movies_data.mapper.MovieItemMapper
import com.blaja.movies_data.util.MovieApiConstants
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: HomeViewModel

    @Mock
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Mock
    lateinit var movieMapper: MovieItemMapper

    @Mock
    private lateinit var upcomingMoviesUIState: Observer<UpcomingMoviesUIState>

    @Mock
    private lateinit var topRatedMoviesUIState: Observer<TopRatedMoviesStateUI>

    @Mock
    private lateinit var recommendedMoviesUIState: Observer<RecommendedMoviesStateUI>


    private val params = mapOf(HomeViewModel.QUERY_ONLINE_KEY to true)

    override fun init() {
        viewModel = HomeViewModel(getMoviesUseCase, movieMapper)
        viewModel.movieItemsLiveData.observeForever(upcomingMoviesUIState)
        viewModel.movieTopRatedItemsLiveData.observeForever(topRatedMoviesUIState)
        viewModel.movieRecommendedItemsLiveData.observeForever(recommendedMoviesUIState)
    }


    @Test
    fun `WHEN there is an attempt to retrieve upcoming movies THEN the user sees a loading animation`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                getMoviesUseCase.executeAsync(
                    params,
                    MovieApiConstants.UPCOMING_MOVIE
                )
            ).thenReturn(
                Resource.success(listOf())
            )
            //WHEN
            val spySubject = Mockito.spy(viewModel)
            spySubject.getUpcomingMovies(true)
            //THEN
            Mockito.verify(spySubject, Mockito.times(1))setUpcomingState(UpcomingMoviesUIState.Loading)
        }
    }

    @Test
    fun `GIVEN there is success WHEN retrieving upcoming movies THEN the user sees the upcoming movie list`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                getMoviesUseCase.executeAsync(
                    params,
                    MovieApiConstants.UPCOMING_MOVIE
                )
            ).thenReturn(
                Resource.success(listOf())
            )

            viewModel.getUpcomingMovies(true)

            assertNotNull(viewModel.movieItemsLiveData.value)
            assertEquals(
                UpcomingMoviesUIState.Success(listOf()),
                viewModel.movieItemsLiveData.value
            )
        }
    }


    @Test
    fun `GIVEN there is an error  WHEN retrieving upcoming movies THEN the user sees an error message`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                getMoviesUseCase.executeAsync(
                    params,
                    MovieApiConstants.UPCOMING_MOVIE
                )
            ).thenReturn(
                Resource.error(ERROR_MESSAGE))

            viewModel.getUpcomingMovies(true)

            assertNotNull(viewModel.movieItemsLiveData.value)
            assertEquals(
                UpcomingMoviesUIState.Error(ERROR_MESSAGE),
                viewModel.movieItemsLiveData.value
            )
        }
    }

    @Test
    fun `GIVEN there is success WHEN retrieving top rated movies THEN the user sees the top rated movie list`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                getMoviesUseCase.executeAsync(
                    params,
                    MovieApiConstants.TOP_RATED_MOVIE
                )
            ).thenReturn(
                Resource.success(listOf())
            )

            viewModel.getTopRatedMovies(true)

            assertNotNull(viewModel.movieTopRatedItemsLiveData.value)
            assertEquals(
                TopRatedMoviesStateUI.Success(listOf()),
                viewModel.movieTopRatedItemsLiveData.value
            )
        }
    }


    @Test
    fun `GIVEN there is an error  WHEN retrieving top rated movies THEN the user sees an error message`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                getMoviesUseCase.executeAsync(
                    params,
                    MovieApiConstants.TOP_RATED_MOVIE
                )
            ).thenReturn(
                Resource.error(ERROR_MESSAGE))

            viewModel.getTopRatedMovies(true)

            assertNotNull(viewModel.movieTopRatedItemsLiveData.value)
            assertEquals(
                TopRatedMoviesStateUI.Error(ERROR_MESSAGE),
                viewModel.movieTopRatedItemsLiveData.value
            )
        }
    }



}