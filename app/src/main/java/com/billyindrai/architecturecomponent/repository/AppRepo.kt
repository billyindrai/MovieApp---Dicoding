package com.billyindrai.architecturecomponent.repository

import androidx.lifecycle.LiveData
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

interface AppRepo {
    fun getPopularMovie(): LiveData<ArrayList<Movie>>

    fun getPopularTvShow(): LiveData<ArrayList<TvShows>>

    fun getDetailMovie(id : Int) : LiveData<Movie>

    fun getDetailTvShow(id : Int) : LiveData<TvShows>
}