package com.blaja.home_feature.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.blaja.core.BaseFragment
import com.blaja.core.extension.visibleByCondition
import com.blaja.home_feature.R
import com.blaja.home_feature.databinding.FragmentDetailMovieBinding
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.model.Video
import com.blaja.movies_data.presentation.DetailMovieViewModel
import com.blaja.movies_data.presentation.VideosRequestUIState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieFragment : BaseFragment<DetailMovieViewModel>() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    override val mViewModel: DetailMovieViewModel by viewModel()
    private val args by navArgs<DetailMovieFragmentArgs>()
    private lateinit var movieDetail: MovieItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObserver()
        mViewModel.requestVideoList(movieDetail.id.toString())
    }

    private fun setupObserver() {
        mViewModel.detailVideosLiveData.observe(
            viewLifecycleOwner,
            Observer(::renderVideosStates)
        )
    }

    private fun initViews() {
        movieDetail = args.movie
        binding.movieBackgroundIv.let {
            Glide
                .with(requireContext())
                .load("https://image.tmdb.org/t/p/w500/${movieDetail.posterPath}")
                .placeholder(R.drawable.ic_movie_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .into(it)

            binding.bottomSheet.detailTv.text =
                getString(R.string.detail_label, movieDetail.overview)
            binding.bottomSheet.titleTv.text = movieDetail.title
            binding.bottomSheet.originalTitleTv.text =
                getString(R.string.original_title, movieDetail.originalTitle)
            binding.bottomSheet.ratingTv.text =
                getString(R.string.rating_label, movieDetail.voteAverage.toString())
            binding.bottomSheet.detailTv.visibleByCondition(!movieDetail.overview.isNullOrBlank())
            binding.bottomSheet.originalTitleTv.visibleByCondition(!movieDetail.overview.isNullOrBlank())
        }
    }


    private fun renderVideosStates(videosRequestUIState: VideosRequestUIState) {
        when (videosRequestUIState) {
            is VideosRequestUIState.Loading -> {}
            is VideosRequestUIState.Success -> displayVideos(videosRequestUIState.videos)
            is VideosRequestUIState.Default -> Unit
            is VideosRequestUIState.Error -> {}
            else -> Unit
        }
    }

    private fun displayVideos(videos: List<Video>) {
        binding.bottomSheet.trailerBtn.visibleByCondition(videos.isNotEmpty())
        binding.bottomSheet.trailerBtn.setOnClickListener { playVideo(videos[0]) }
    }

    private fun playVideo(item: Video) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://${item.key}"))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}