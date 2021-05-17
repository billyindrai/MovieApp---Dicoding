package com.billyindrai.architecturecomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private var findMovie: LiveData<Movie>? = null
    private var findTvShows: LiveData<TvShows>? = null

    fun getAllMovies(sort: String): LiveData<PagedList<Movie>> =
        LivePagedListBuilder(repository.getAllMovieFromDb(sort), 10).build()

    fun getAllTVShows(sort: String): LiveData<PagedList<TvShows>> =
        LivePagedListBuilder(repository.getAllTvFromDb(sort), 10).build()

    fun findMovie(id: Int): LiveData<Movie>? {
        findMovie = repository.findMovieFromDb(id)
        return findMovie
    }

    fun findTVShow(id: Int): LiveData<TvShows>? {
        findTvShows = repository.findTvFromDb(id)
        return findTvShows
    }

    fun deleteMovie(id: Int) = viewModelScope.launch { repository.deleteMovie(id) }

    fun deleteTVShow(id: Int) = viewModelScope.launch { repository.deleteTv(id) }

}