package com.billyindrai.architecturecomponent.repository


import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.PopMovie
import com.billyindrai.architecturecomponent.data.PopTv
import com.billyindrai.architecturecomponent.data.TvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopMovie>

    @GET("/3/movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    @GET("/3/tv/popular")
    fun getPopularTVs(@Query("api_key") apiKey: String): Call<PopTv>

    @GET("/3/tv/{tv_id}")
    fun getDetailTV(@Path("tv_id") id: Int, @Query("api_key") apiKey: String): Call<TvShows>
}