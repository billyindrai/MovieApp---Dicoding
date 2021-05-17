package com.billyindrai.architecturecomponent.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.ItemFilmBinding
import com.bumptech.glide.Glide

class TvShowsPagingAdapter: PagedListAdapter<TvShows, TvShowsPagingAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsPagingAdapter.TVShowViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TvShows)
    }

    inner class TVShowViewHolder(private val binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(TvShow: TvShows){
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + TvShow.poster)
                    .into(ivPoster)
                tvTitle.text = TvShow.title
                tvRating.text = TvShow.rating.toString()

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(TvShow)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvShows> = object : DiffUtil.ItemCallback<TvShows>() {
            override fun areItemsTheSame(oldData: TvShows, newData: TvShows): Boolean {
                return oldData.title == newData.title && oldData.description == newData.description
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldData: TvShows, newData: TvShows): Boolean {
                return oldData == newData
            }
        }
    }
}