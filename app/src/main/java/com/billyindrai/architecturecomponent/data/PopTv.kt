package com.billyindrai.architecturecomponent.data

import com.google.gson.annotations.SerializedName

data class PopTv(
    @SerializedName("results")
    val result: ArrayList<TvShows>
)