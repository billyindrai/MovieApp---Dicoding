package com.billyindrai.architecturecomponent.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.billyindrai.architecturecomponent.EspressoIdling
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository : AppRepo {
    private val API_KEY = "b743213571e7b5422b800461f07dd2f0"

    override fun getPopularMovie(): LiveData<ArrayList<Movie>>{
        val popMovie = MutableLiveData<ArrayList<Movie>>()
        EspressoIdling.increment()
        Retrofit.create().getPopularMovies(API_KEY).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    popMovie.postValue(response.body()?.result)

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }

                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("getPopularMovies: ", t.message.toString())
            }
        })
        return popMovie
    }

    override fun getPopularTvShow(): LiveData<ArrayList<TvShows>> {
        val popTvShow = MutableLiveData<ArrayList<TvShows>>()
        EspressoIdling.increment()
        Retrofit.create().getPopularTVs(API_KEY).enqueue(object : Callback<TvShows> {
            override fun onResponse(call: Call<TvShows>, response: Response<TvShows>) {
                if (response.isSuccessful) {
                    popTvShow.postValue(response.body()?.result)

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }
                }
            }
            override fun onFailure(call: Call<TvShows>, t: Throwable) {
                Log.e("getPopularTVs: ", t.message.toString())
            }
        })
        return popTvShow
    }

    override fun getDetailMovie(id: Int): LiveData<Movie> {
        val detailMovie = MutableLiveData<Movie>()
        EspressoIdling.increment()
        Retrofit.create().getDetailMovie(id, API_KEY).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    detailMovie.postValue(response.body())

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("getDetailMovie: ", t.message.toString())
            }
        })
        return detailMovie
    }

    override fun getDetailTvShow(id: Int): LiveData<TvShows> {
        val detailTvShow = MutableLiveData<TvShows>()
        EspressoIdling.increment()
        Retrofit.create().getDetailTV(id, API_KEY).enqueue(object : Callback<TvShows> {
            override fun onResponse(call: Call<TvShows>, response: Response<TvShows>) {
                if (response.isSuccessful) {
                    detailTvShow.postValue(response.body())

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }
                }
            }
            override fun onFailure(call: Call<TvShows>, t: Throwable) {
                Log.e("getDetailTV: ", t.message.toString())
            }

        })
        return detailTvShow
    }
}
