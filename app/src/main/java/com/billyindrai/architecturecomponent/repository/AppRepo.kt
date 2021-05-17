package com.billyindrai.architecturecomponent.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

interface AppRepo {
    fun getPopularMovie(): LiveData<ArrayList<Movie>>

    fun getPopularTvShow(): LiveData<ArrayList<TvShows>>

    fun getDetailMovie(id : Int) : LiveData<Movie>

    fun getDetailTvShow(id : Int) : LiveData<TvShows>

    fun getAllMovieFromDb(sort : String) : DataSource.Factory<Int, Movie>

    fun getAllTvFromDb(sort : String) : DataSource.Factory<Int, TvShows>

    fun findMovieFromDb(id: Int) : LiveData<Movie>?

    fun findTvFromDb(id: Int) : LiveData<TvShows>?

    suspend fun insertMovie(movie: Movie)

    suspend fun insertTv(tvShow: TvShows)

    suspend fun deleteMovie(id: Int)

    suspend fun deleteTv(id: Int)
}