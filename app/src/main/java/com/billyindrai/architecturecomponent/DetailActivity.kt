package com.billyindrai.architecturecomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA= "EXTRA"
        const val EXTRA_SELECT = "EXTRA_SELECT"
        const val EXTRA_MOVIE = 100
        const val EXTRA_TV = 101
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if (intent.extras != null) {
            val id = intent.getIntExtra(EXTRA, 0)
            val select = intent.getIntExtra(EXTRA_SELECT, 0)

            binding.pb.visibility = View.VISIBLE

            viewModel.setSelectedData(id)
            when (select) {
                EXTRA_MOVIE -> {
                    setActionBarTittle("Movie")
                    viewModel.getDataMovie().observe(this, { movie ->
                        bindViewMovie(movie)
                        binding.pb.visibility = View.GONE
                    })
                }
                EXTRA_TV -> {
                    setActionBarTittle("Tv Show")
                    viewModel.getDataTvShows().observe(this, { tv ->
                        bindViewTvShows(tv)
                        binding.pb.visibility = View.GONE
                    })
                }
                else -> return
            }
        }
    }



    private fun bindViewMovie(movie: Movie) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w185" + movie.poster)
                .into(ivDetail)
            tvTitleDetail.text = movie.title
            tvRatingDetail.text = movie.rating.toString()
            tvDateDetail.text = movie.date
            tvDurationDetail.text = getString(R.string.duration, movie.duration.toString())
            for (i in movie.genres.indices) {
                if (i < movie.genres.size - 1) {
                    tvGenreDetail.append("${movie.genres[i].name}, ")
                } else {
                    tvGenreDetail.append(movie.genres[i].name)
                }
            }
            tvDescriptionsDetail.text = movie.description
        }
    }

    private fun bindViewTvShows(tvShows: TvShows) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w185" + tvShows.poster)
                .into(ivDetail)
            tvTitleDetail.text = tvShows.title
            tvRatingDetail.text = tvShows.rating.toString()
            tvDateDetail.text = tvShows.date
            tvDurationDetail.text = getString(R.string.episodes, tvShows.episodes.toString())
            tvSeasonsDetail.text = getString(R.string.seasons, tvShows.seasons.toString())
            for (i in tvShows.genres.indices) {
                if (i < tvShows.genres.size - 1) {
                    tvGenreDetail.append("${tvShows.genres[i].name}, ")
                } else {
                    tvGenreDetail.append(tvShows.genres[i].name)
                }
            }
            tvDescriptionsDetail.text = tvShows.description
        }
    }

    private fun setActionBarTittle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}