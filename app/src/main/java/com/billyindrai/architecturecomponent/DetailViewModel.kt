package com.billyindrai.architecturecomponent

import androidx.lifecycle.ViewModel
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

class DetailViewModel : ViewModel() {

    private var id: Int = 0

    fun setSelectedData(id: Int) {
        this.id = id
    }

    fun getDataMovie(): Movie? {
        var movie: Movie? = null
        for (data in Dummy.dummyMovies()) {
            if (data.id == id) {
                movie = data
            }
        }
        return movie
    }

    fun getDataTvShows(): TvShows? {
        var tvShows: TvShows? = null
        for (data in Dummy.dummyTvShows()) {
            if (data.id == id) {
                tvShows = data
            }
        }
        return tvShows
    }
}