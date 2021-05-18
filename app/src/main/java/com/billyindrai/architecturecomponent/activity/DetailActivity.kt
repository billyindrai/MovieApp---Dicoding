package com.billyindrai.architecturecomponent.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.billyindrai.architecturecomponent.R
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.ActivityDetailBinding
import com.billyindrai.architecturecomponent.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA= "EXTRA"
        const val EXTRA_SELECT = "EXTRA_SELECT"
        const val EXTRA_MOVIE = 100
        const val EXTRA_TV = 101
    }
    private lateinit var binding: ActivityDetailBinding
    private val checked: Int = R.drawable.ic_baseline_favorite_border_24
    private val unChecked: Int = R.drawable.ic_baseline_favorite_24

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val viewModel : DetailViewModel by viewModels()
        var statusFav = false

        var id = 0
        var select: Int = 0
        var movieData: Movie = Movie(id)
        var tvShowData: TvShows = TvShows(id)

        if (intent.extras != null) {

            id = intent.getIntExtra(EXTRA, 0)
            select = intent.getIntExtra(EXTRA_SELECT, 0)

            showLoading(true)

            when (select) {
                EXTRA_MOVIE -> {
                    setActionBarTittle("Movie")
                    viewModel.findMovie(id)?.observe(this, { movie ->
                        if (movie != null){
                            movieData = movie
                            bindViewMovie(movie)
                            statusFav = true
                            binding.btnFavorite.setImageResource(checked)
                            showLoading(false)
                        } else {
                            viewModel.setSelectedMovie(id)
                            viewModel.getSelectedMovie().observe(this, { movieDetail ->
                                movieData = movieDetail
                                bindViewMovie(movieDetail)
                                binding.btnFavorite.setImageResource(unChecked)
                                showLoading(false)
                            })
                        }
                    })
                }
                EXTRA_TV -> {
                    setActionBarTittle("Tv Show")
                    viewModel.findTv(id)?.observe(this, { tvShow ->
                        if (tvShow != null){
                            tvShowData = tvShow
                            bindViewTvShows(tvShow)
                            statusFav = true
                            binding.btnFavorite.setImageResource(checked)
                            showLoading(false)
                        } else {
                            viewModel.setSelectedTV(id)
                            viewModel.getSelectedTVShow().observe(this, { tvDetail ->
                                tvShowData = tvDetail
                                bindViewTvShows(tvDetail)
                                binding.btnFavorite.setImageResource(unChecked)
                                showLoading(false)
                            })
                        }
                    })
                }
                else -> return
            }
        }
        binding.btnFavorite.setOnClickListener { view ->
            if (!statusFav) {
                when(select){
                    EXTRA_MOVIE -> viewModel.insertMovie(movieData)
                    EXTRA_TV -> viewModel.insertTv(tvShowData)
                    else -> return@setOnClickListener
                }
                binding.btnFavorite.setImageResource(checked)
                Snackbar.make(view, getString(R.string.add_favorite), Snackbar.LENGTH_SHORT).show()
            } else {
                when(select){
                    EXTRA_MOVIE -> viewModel.deleteMovie(movieData)
                    EXTRA_TV -> viewModel.deleteTv(tvShowData)
                    else -> return@setOnClickListener
                }
                binding.btnFavorite.setImageResource(unChecked)
                Snackbar.make(view, getString(R.string.remove_favorite), Snackbar.LENGTH_SHORT).show()
            }
            statusFav = !statusFav
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
            for (i in movie.genres?.indices!!) {
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
            for (i in tvShows.genres?.indices!!) {
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pb.visibility = View.VISIBLE
        } else {
            binding.pb.visibility = View.GONE
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