package com.blaja.home_feature.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.blaja.home_feature.R
import com.blaja.home_feature.databinding.ItemMovieBinding
import com.blaja.movies_data.model.MovieItem
import com.blaja.movies_data.util.NetworkConstants.MOVIE_IMAGE_BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class MoviesAdapter internal constructor(
    private val movies: List<MovieItem>?,
    private val listener: OnMovieClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies!![position]
        holder.binding.moviePhoto.let {
            Glide
                .with(holder.binding.moviePhoto.context)
                .load(MOVIE_IMAGE_BASE_URL + item.posterPath)
                .placeholder(R.drawable.ic_movie_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .into(it)
        }
        holder.binding.moviePhoto.transitionName = holder.binding.moviePhoto.context.getString(
            R.string.movie_poster_transition_name,
            item.posterPath
        )
        holder.binding.root.setOnClickListener {
            listener.setOnMovieClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    interface OnMovieClickListener {
        fun setOnMovieClickListener(movie: MovieItem)
    }

}

