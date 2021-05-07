package com.billyindrai.architecturecomponent

import androidx.lifecycle.ViewModel
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

class ViewModel: ViewModel() {

    fun getMovies() : List<Movie> = Dummy.dummyMovies()

    fun getTVShow() : List<TvShows> = Dummy.dummyTvShows()
}