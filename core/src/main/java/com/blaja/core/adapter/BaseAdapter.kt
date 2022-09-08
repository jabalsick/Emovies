package com.blaja.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView



abstract class BaseAdapter<T : BaseParcelable>(
    private val itemClick: (T) -> Unit = {}
) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback()) {

    var mCurrentList = arrayListOf<T?>()
    private var mPosition: Int = 0

    @LayoutRes
    abstract fun layoutRes(): Int

    abstract fun onViewHolderCreated(viewHolder: BaseViewHolder<T>)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutRes(),
            parent,
            false
        )
        return BaseViewHolder<T>(binding).apply {
            onViewHolderCreated(this)
            itemView.setOnClickListener {
                this@BaseAdapter.mPosition = adapterPosition
                itemClick(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind()
    }


    fun addList(newList: List<T>) {
        mCurrentList.addAll(newList)
        submitList(mCurrentList.toMutableList())
    }


    fun clearCurrentList() {
        mCurrentList.clear()
        submitList(mCurrentList.toMutableList())
    }

}

class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.executePendingBindings()
    }
}

class BaseItemCallback<T : BaseParcelable> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.unique().toString() == newItem.unique().toString()

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.equals(newItem)
}

interface BaseParcelable {
    fun unique(): Any
}
