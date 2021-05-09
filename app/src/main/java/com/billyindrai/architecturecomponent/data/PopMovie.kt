package com.billyindrai.architecturecomponent.data

import com.google.gson.annotations.SerializedName

data class PopMovie(
    @SerializedName("results")
    val result: ArrayList<Movie>
)