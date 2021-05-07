package com.billyindrai.architecturecomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.ItemFilmBinding
import java.util.ArrayList

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.CardViewViewHolder>() {

    private var tvShows = ArrayList<TvShows>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setTvShows(tvList: ArrayList<TvShows>) {
        tvShows.clear()
        tvShows.addAll(tvList)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    inner class CardViewViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShows){
            with(binding){
                ivPoster.setImageResource(tvShows.poster)
                tvTitle.text = tvShows.title
                tvRating.text = tvShows.rating
                tvGenre.text = tvShows.genre

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(tvShows)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShows)
    }
}