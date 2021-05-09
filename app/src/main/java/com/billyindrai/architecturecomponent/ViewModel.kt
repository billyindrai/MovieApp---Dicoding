package com.billyindrai.architecturecomponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.AppRepo
import com.billyindrai.architecturecomponent.repository.AppRepository
import java.util.*

class ViewModel(private val repository: AppRepo = AppRepository()) : ViewModel() {

    fun getMovies() : LiveData<ArrayList<Movie>> = repository.getPopularMovie()

    fun getTVShow() : LiveData<ArrayList<TvShows>> = repository.getPopularTvShow()
}