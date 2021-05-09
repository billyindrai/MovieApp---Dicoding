package com.billyindrai.architecturecomponent.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

class FakeRepo (private val fakeData: FakeData) : AppRepo{

    companion object {
        @Volatile
        private var instance: FakeRepo? = null

        fun getInstance(data: FakeData): FakeRepo =
            instance ?: synchronized(this) {
                FakeRepo(data).apply { instance = this }
            }
    }

    override fun getPopularMovie(): LiveData<ArrayList<Movie>> {
        val moviePop= MutableLiveData<ArrayList<Movie>>()
        fakeData.getPopMovie(object : FakeData.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for (response in movieResponses) {
                    val movie = Movie(response.id,
                        null,
                        response.title,
                        response.rating,
                        response.date,
                        response.duration,
                        response.genres,
                        response.description)
                    movieList.add(movie)
                }
                moviePop.postValue(movieList)
            }
        })

        return moviePop
    }

    override fun getPopularTvShow(): LiveData<ArrayList<TvShows>> {
        val tvPop = MutableLiveData<ArrayList<TvShows>>()
        fakeData.getPopTv(object : FakeData.LoadTVShowCallback {
            override fun onAllTVShowReceived(tvshowResponses: List<TvShows>) {
                val tvList = ArrayList<TvShows>()
                for (response in tvshowResponses) {
                    val tvShow = TvShows(response.id,
                        null,
                        response.title,
                        response.rating,
                        response.date,
                        response.episodes,
                        response.seasons,
                        response.genres,
                        response.description)
                    tvList.add(tvShow)
                }
                tvPop.postValue(tvList)
            }
        })
        return tvPop
    }

    override fun getDetailMovie(id: Int): LiveData<Movie> {
        val movieDet = MutableLiveData<Movie>()
        fakeData.getMovieDet(id, object : FakeData.LoadMovieContentCallback{
            override fun onContentReceived(movieResponse: Movie) {
                movieDet.postValue(movieResponse)
            }
        })
        return movieDet
    }

    override fun getDetailTvShow(id: Int): LiveData<TvShows> {
        val tvDet = MutableLiveData<TvShows>()
        fakeData.getTvDet(id, object : FakeData.LoadTVContentCallback{
            override fun onContentReceived(tvshowResponse: TvShows) {
                tvDet.postValue(tvshowResponse)
            }
        })
        return tvDet
    }


}