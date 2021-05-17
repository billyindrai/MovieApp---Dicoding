package com.billyindrai.architecturecomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val repository: AppRepository) : ViewModel() {
    private var movieData: LiveData<ArrayList<Movie>> = repository.getPopularMovie()
    private var tvShowData: LiveData<ArrayList<TvShows>> = repository.getPopularTvShow()

    fun getMovie() : LiveData<ArrayList<Movie>> = movieData
    fun getTvShow() : LiveData<ArrayList<TvShows>> = tvShowData
}