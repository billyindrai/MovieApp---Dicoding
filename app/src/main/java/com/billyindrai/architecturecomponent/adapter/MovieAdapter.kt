package com.billyindrai.architecturecomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.databinding.ItemFilmBinding
import com.bumptech.glide.Glide
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    private var movies = ArrayList<Movie>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setMovies(movieList: ArrayList<Movie>) {
        movies.clear()
        movies.addAll(movieList)
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
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class CardViewViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + movie.poster)
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvRating.text = movie.rating.toString()
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}