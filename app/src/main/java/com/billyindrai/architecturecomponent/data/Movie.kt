package com.billyindrai.architecturecomponent.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "favMovie")
data class Movie (
    @SerializedName("id")
    @PrimaryKey
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
    @SerializedName("genres")
    val genres: @RawValue List<Genre>? = null,
    @SerializedName("overview")
    var description: String? = null
) : Parcelable