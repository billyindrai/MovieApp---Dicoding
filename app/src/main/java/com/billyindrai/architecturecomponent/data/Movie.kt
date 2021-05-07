package com.billyindrai.architecturecomponent.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    var poster: Int = 0,
    var title: String? = null,
    var rating: String? = null,
    var date: String? = null,
    var duration: String? = null,
    var genre: String? = null,
    var description: String? = null
) : Parcelable