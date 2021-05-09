package com.billyindrai.architecturecomponent.repository

import android.os.Handler
import android.os.Looper
import com.billyindrai.architecturecomponent.EspressoIdling
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

class FakeData private constructor(private val data: Dummy) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: FakeData? = null

        fun getInstance(data: Dummy): FakeData =
            instance ?: synchronized(this) {
                FakeData(data).apply { instance = this }
            }
    }

    fun getPopMovie(callback: LoadMoviesCallback) {
        EspressoIdling.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(data.generateDummyMovies())
            EspressoIdling.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getPopTv(callback: LoadTVShowCallback) {
        EspressoIdling.increment()
        handler.postDelayed({
            callback.onAllTVShowReceived(data.generateDummyTVShow())
            EspressoIdling.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieDet(moduleId: Int, callback: LoadMovieContentCallback) {
        EspressoIdling.increment()
        handler.postDelayed({
            lateinit var movies: Movie
            data.generateDummyMovies().forEach { movie ->
                if (movie.id == moduleId) {
                    movies = movie
                }
            }
            callback.onContentReceived(movies)
            EspressoIdling.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvDet(moduleId: Int, callback: LoadTVContentCallback) {
        EspressoIdling.increment()
        handler.postDelayed({
            lateinit var tvShow: TvShows
            data.generateDummyTVShow().forEach { tv ->
                if (tv.id == moduleId) {
                    tvShow = tv
                }
            }
            callback.onContentReceived(tvShow)
            EspressoIdling.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<Movie>)
    }

    interface LoadTVShowCallback {
        fun onAllTVShowReceived(tvshowResponses: List<TvShows>)
    }

    interface LoadMovieContentCallback {
        fun onContentReceived(movieResponse: Movie)
    }

    interface LoadTVContentCallback {
        fun onContentReceived(tvshowResponse: TvShows)
    }
}