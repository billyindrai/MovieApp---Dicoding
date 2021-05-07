package com.billyindrai.architecturecomponent.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var rating: Float? = null,
    @SerializedName("release_date")
    var date: String? = null,
    @SerializedName("runtime")
    var duration: Int? = null,

//    var genre: String? = null,
    @SerializedName("overview")
    var description: String? = null,
    @SerializedName("results")
    val result: ArrayList<Movie>
) : Parcelable