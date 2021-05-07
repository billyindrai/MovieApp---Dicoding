package com.billyindrai.architecturecomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA= "EXTRA"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val id = intent.getIntExtra(EXTRA, 0)
        viewModel.setSelectedData(id)
        if (id < 100) {
            val movie = viewModel.getDataMovie()
            if (movie != null) {
                setActionBarTittle("Movie")
                bindViewMovie(movie)
            }
        } else {
            val tvShows = viewModel.getDataTvShows()
            if (tvShows != null) {
                setActionBarTittle("Tv Show")
                bindViewTvShows(tvShows)
            }
        }

    }


    private fun bindViewMovie(movie: Movie) {
        with(binding) {
            ivDetail.setImageResource(movie.poster)
            tvTitleDetail.text = movie.title
            tvRatingDetail.text = movie.rating
            tvDateDetail.text = movie.date
            tvDurationDetail.text = movie.duration
            tvGenreDetail.text = movie.genre
            tvDescriptionsDetail.text = movie.description
        }
    }

    private fun bindViewTvShows(tvShows: TvShows) {
        with(binding) {
            ivDetail.setImageResource(tvShows.poster)
            tvTitleDetail.text = tvShows.title
            tvRatingDetail.text = tvShows.rating
            tvDateDetail.text = tvShows.date
            tvDurationDetail.text = tvShows.duration
            tvGenreDetail.text = tvShows.genre
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