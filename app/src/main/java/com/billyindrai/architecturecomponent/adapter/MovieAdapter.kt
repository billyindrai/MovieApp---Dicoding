package com.billyindrai.architecturecomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.databinding.ItemFilmBinding
import java.util.ArrayList

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
                ivPoster.setImageResource(movie.poster)
                tvTitle.text = movie.title
                tvRating.text = movie.rating
                tvGenre.text = movie.genre

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