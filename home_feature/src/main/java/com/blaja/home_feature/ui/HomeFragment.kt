package com.blaja.home_feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.blaja.core.BaseFragment
import com.blaja.core.extension.ConnectionUtils
import com.blaja.home_feature.R
import com.blaja.home_feature.databinding.FragmentHomeBinding
import com.blaja.home_feature.ui.view.ShowcaseDialog
import com.blaja.home_feature.ui.view.ShowcaseDialog.Companion.SHOWCASE_DIALOG_TAG
import com.blaja.home_feature.ui.view.ShowcaseLayout
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.presentation.HomeViewModel
import com.blaja.movies_data.presentation.RecommendedMoviesStateUI
import com.blaja.movies_data.presentation.TopRatedMoviesStateUI
import com.blaja.movies_data.presentation.UpcomingMoviesUIState
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeViewModel>(), ShowcaseLayout.OnMovieClickListener,
    ShowcaseDialog.OnLanguageClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override val mViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        refreshHomeData()
    }

    private fun refreshHomeData() {
       val isOnline = ConnectionUtils.isOnline(requireContext())
        mViewModel.getUpcomingMovies(isOnline)
        mViewModel.getTopRatedMovies(isOnline)
    }

    private fun setupListeners() {
        binding.upcomingView.setListener(this)
        binding.topRatedView.setListener(this)
        binding.recommendedView.setListener(this)
        binding.recommendedView.setOnActionListener { openDialog() }
    }

    private fun setupObservers() {
        mViewModel.movieItemsLiveData.observe(
            viewLifecycleOwner,
            Observer(::renderUpcomingMoviesUI)
        )
        mViewModel.movieTopRatedItemsLiveData.observe(
            viewLifecycleOwner,
            Observer(::renderTopRatedMoviesUI)
        )
        mViewModel.movieRecommendedItemsLiveData.observe(
            viewLifecycleOwner,
            Observer(::renderRecommendedMoviesUI)
        )
    }

    private fun renderUpcomingMoviesUI(upcomingMoviesUIState: UpcomingMoviesUIState?) {
        when (upcomingMoviesUIState) {
            is UpcomingMoviesUIState.Loading -> displayLoading()
            is UpcomingMoviesUIState.Success -> displayUpcomingMovies(upcomingMoviesUIState.movies)
            is UpcomingMoviesUIState.Default -> Unit
            is UpcomingMoviesUIState.Error -> {
                displayError()
            }
            else -> Unit
        }
    }

    private fun renderTopRatedMoviesUI(topRatedMoviesUIState: TopRatedMoviesStateUI?) {
        when (topRatedMoviesUIState) {
            is TopRatedMoviesStateUI.Loading -> displayTopRatedLoading()
            is TopRatedMoviesStateUI.Success -> {
                displayTopRatedMovies(topRatedMoviesUIState.movies)
                updateRecommendedMoviesSection(topRatedMoviesUIState.movies)
            }
            is TopRatedMoviesStateUI.Default -> Unit
            is TopRatedMoviesStateUI.Error -> {displayError()}
            else -> Unit
        }
    }

    private fun renderRecommendedMoviesUI(recommendedMoviesUIState: RecommendedMoviesStateUI?) {
        when (recommendedMoviesUIState) {
            is RecommendedMoviesStateUI.Loading -> displayRecommendedLoading()
            is RecommendedMoviesStateUI.Success -> displayRecommendedMovies(recommendedMoviesUIState.movies)
            is RecommendedMoviesStateUI.Default -> Unit
            is RecommendedMoviesStateUI.Error -> {displayError()}
            else -> Unit
        }
    }

    private fun displayUpcomingMovies(movies: List<MovieItem>?) {
        binding.upcomingView.setMovieList(movies)
    }

    private fun displayTopRatedMovies(movies: List<MovieItem>?) {
        binding.topRatedView.setMovieList(movies)
    }

    private fun displayRecommendedMovies(movies: List<MovieItem>?) {
        binding.recommendedView.setMovieList(movies)
    }

    private fun displayLoading() {
        binding.upcomingView.displayLoading()
    }

    private fun displayTopRatedLoading() {
        binding.topRatedView.displayLoading()
    }

    private fun displayRecommendedLoading() {
        binding.recommendedView.displayLoading()
    }

    private fun displayError() {
        Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_LONG).show()
    }

    private fun updateRecommendedMoviesSection(movies: List<MovieItem>?) {
        mViewModel.updateRecommendedMovies(movies)
        mViewModel.updateAvailableLanguages()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(movie: MovieItem) {
        goToMovieDetail(movie)
    }

    private fun openDialog() {
        ShowcaseDialog(
            mViewModel.moviesAvailableLanguages,
            this
        ).show(parentFragmentManager, SHOWCASE_DIALOG_TAG)
    }

    private fun goToMovieDetail(movie: MovieItem) {
        val action = HomeFragmentDirections.actionHomefragmentToDetailMovieFragment(movie)
        findNavController().navigate(action)
    }

    override fun setOnLanguageClickListener(lang: String) {
        mViewModel.filterRecommendedMoviesByLanguage(lang)
    }

}
