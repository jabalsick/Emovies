package com.blaja.home_feature.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blaja.core.extension.gone
import com.blaja.core.extension.visible
import com.blaja.core.extension.visibleByCondition
import com.blaja.home_feature.R
import com.blaja.home_feature.databinding.ShowcaseLayoutBinding
import com.blaja.home_feature.ui.adapter.MoviesAdapter
import com.blaja.movies_data.model.MovieItem

class ShowcaseLayout @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr), MoviesAdapter.OnMovieClickListener {

    private var binding = ShowcaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var moviesAdapter: MoviesAdapter? = null
    lateinit var mListener: OnMovieClickListener

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ShowcaseLayout, 0, 0).apply {
            try {
                binding.showcaseTitleTv.text = getString(R.styleable.ShowcaseLayout_label)
                binding.filterIv.isVisible =
                    getBoolean(R.styleable.ShowcaseLayout_has_action, false)
                setLayoutManager(getInt(R.styleable.ShowcaseLayout_type, LINEAR_SHOWCASE))
            } finally {
                recycle()
            }
        }
    }


    private fun setLayoutManager(type: Int) {
        when (type) {
            LINEAR_SHOWCASE -> binding.showcaseRv.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            )
            GRID_SHOWCASE -> {binding.showcaseRv.layoutManager = GridLayoutManager(context, 2) }
        }

    }

    fun setListener(listener: OnMovieClickListener) {
        mListener = listener
    }

    fun setMovieList(items: List<MovieItem>?) {
        binding.progressBar.gone()
        displayEmptyState(items.isNullOrEmpty())
        if (!items.isNullOrEmpty()) {
            moviesAdapter = MoviesAdapter(items, this)
            binding.showcaseRv.let {
                it.visible()
                it.adapter = moviesAdapter
                it.setHasFixedSize(true)
            }
        }
    }

    private fun displayEmptyState(isEmpty: Boolean) {
        binding.emptyStateTv.visibleByCondition(isEmpty)
        binding.showcaseRv.visibleByCondition(!isEmpty)
    }

    fun displayLoading() {
        binding.progressBar.visible()
    }

    override fun setOnMovieClickListener(movie: MovieItem) {
        mListener.onClick(movie)
    }

    fun setOnActionListener(listener: () -> Unit) {
        binding.filterIv.setOnClickListener {
            listener()
        }
    }

    interface OnMovieClickListener {
        fun onClick(movie: MovieItem)
    }

    companion object {
        const val LINEAR_SHOWCASE = 0
        const val GRID_SHOWCASE = 1
    }
}
