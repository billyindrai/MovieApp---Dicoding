package com.billyindrai.architecturecomponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.AppRepo
import com.billyindrai.architecturecomponent.repository.AppRepository

class DetailViewModel(private val repository: AppRepo = AppRepository()) : ViewModel() {

    private var id: Int = 0

    fun setSelectedData(id: Int) {
        this.id = id
    }

    fun getDataMovie(): LiveData<Movie> {
        return repository.getDetailMovie(id)
    }

    fun getDataTvShows(): LiveData<TvShows> {
        return repository.getDetailTvShow(id)
    }
}