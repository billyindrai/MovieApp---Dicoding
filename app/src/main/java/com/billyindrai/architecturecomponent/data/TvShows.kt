package com.billyindrai.architecturecomponent.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TvShows (
        @SerializedName("id")
        val id: Int,
        @SerializedName("poster_path")
        var poster: String? = null,
        @SerializedName("name")
        var title: String? = null,
        @SerializedName("vote_average")
        var rating: Float? = null,
        @SerializedName("first_air_date")
        var date: String? = null,
        @SerializedName("number_of_episodes")
        var episodes: Int? = null,
        @SerializedName("number_of_seasons")
        var seasons: Int? = null,
        @SerializedName("genres")
        val genres: @RawValue List<Genre>,
        @SerializedName("overview")
        var description: String? = null,
        @SerializedName("results")
        val result: ArrayList<TvShows>
) : Parcelable