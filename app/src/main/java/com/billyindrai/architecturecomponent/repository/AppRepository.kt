package com.billyindrai.architecturecomponent.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.billyindrai.architecturecomponent.EspressoIdling
import com.billyindrai.architecturecomponent.Sorting
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.PopMovie
import com.billyindrai.architecturecomponent.data.PopTv
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.database.DatabaseDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import androidx.paging.DataSource

class AppRepository  @Inject constructor (private val databaseDAO: DatabaseDAO, private val api: API): AppRepo {
    private val API_KEY = "b743213571e7b5422b800461f07dd2f0"

    private var findMovieDb: LiveData<Movie>? = null
    private var findTvDb: LiveData<TvShows>? = null

    override fun getPopularMovie(): LiveData<ArrayList<Movie>>{
        val popMovie = MutableLiveData<ArrayList<Movie>>()
        EspressoIdling.increment()
        api.getPopularMovies(API_KEY).enqueue(object : Callback<PopMovie> {
            override fun onResponse(call: Call<PopMovie>, response: Response<PopMovie>) {
                if (response.isSuccessful) {
                    popMovie.postValue(response.body()?.result)

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }

                }
            }
            override fun onFailure(call: Call<PopMovie>, t: Throwable) {
                Log.e("getPopularMovies: ", t.message.toString())
            }
        })
        return popMovie
    }

    override fun getPopularTvShow(): LiveData<ArrayList<TvShows>> {
        val popTvShow = MutableLiveData<ArrayList<TvShows>>()
        EspressoIdling.increment()
        api.getPopularTVs(API_KEY).enqueue(object : Callback<PopTv> {
            override fun onResponse(call: Call<PopTv>, response: Response<PopTv>) {
                if (response.isSuccessful) {
                    popTvShow.postValue(response.body()?.result)

                    if (!EspressoIdling.getEspressoIdling().isIdleNow) {
                        EspressoIdling.decrement()
                    }
                }
            }
            override fun onFailure(call: Call<PopTv>, t: Throwable) {
                Log.e("getPopularTVs: ", t.message.toString())
            }
        })
        return popTvShow
    }

    override fun getDetailMovie(id: Int): LiveData<Movie> {
        val detailMovie = MutableLiveData<Movie>()
        EspressoIdling.increment()
        api.getDetailMovie(id, API_KEY).enqueue(object : Callback<Movie> {
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
        api.getDetailTV(id, API_KEY).enqueue(object : Callback<TvShows> {
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

    override fun getAllMovieFromDb(sort: String): DataSource.Factory<Int, Movie>  {
        val query = Sorting.getSortedQuery(Sorting.TYPE_MOVIE, sort)
        return databaseDAO.getAllMovies(query)
    }

    override fun getAllTvFromDb(sort: String): DataSource.Factory<Int, TvShows>  {
        val query = Sorting.getSortedQuery(Sorting.TYPE_TVSHOW, sort)
        return databaseDAO.getAllTvs(query)
    }

    override fun findMovieFromDb(id: Int): LiveData<Movie>? {
        findMovieDb = databaseDAO.findMovie(id)
        return findMovieDb
    }

    override fun findTvFromDb(id: Int): LiveData<TvShows>? {
        findTvDb = databaseDAO.findTv(id)
        return findTvDb
    }

    override suspend fun insertMovie(movie: Movie) {
        databaseDAO.insert(movie)
    }

    override suspend fun insertTv(tvShow: TvShows) {
        databaseDAO.insert(tvShow)
    }

    override suspend fun deleteMovie(movie: Movie) {
        databaseDAO.deleteMovie(movie)
    }

    override suspend fun deleteTv(tvShow: TvShows) {
        databaseDAO.deleteTv(tvShow)
    }


}

