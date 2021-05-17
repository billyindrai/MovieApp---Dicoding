package com.billyindrai.architecturecomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private lateinit var movie : LiveData<Movie>
    private lateinit var tv : LiveData<TvShows>
    private var findMovie: LiveData<Movie>? = null
    private var findTv: LiveData<TvShows>? = null

    fun setSelectedMovie(id: Int) { movie = repository.getDetailMovie(id) }

    fun setSelectedTV(id: Int) { tv = repository.getDetailTvShow(id) }

    fun getSelectedMovie() : LiveData<Movie> = movie

    fun getSelectedTVShow() : LiveData<TvShows> = tv

    fun insertMovie(movie: Movie) = viewModelScope.launch { repository.insertMovie(movie) }

    fun insertTv(tvShow: TvShows) = viewModelScope.launch { repository.insertTv(tvShow) }

    fun deleteMovie(id: Int) = viewModelScope.launch { repository.deleteMovie(id) }

    fun deleteTv(id: Int) = viewModelScope.launch { repository.deleteTv(id) }

    fun findMovie(id: Int): LiveData<Movie>? {
        findMovie = repository.findMovieFromDb(id)
        return findMovie
    }

    fun findTv(id: Int): LiveData<TvShows>? {
        findTv = repository.findTvFromDb(id)
        return findTv
    }
}