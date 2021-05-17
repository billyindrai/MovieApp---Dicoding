package com.billyindrai.architecturecomponent.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "favTv")
data class TvShows (
        @SerializedName("id")
        @PrimaryKey
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
        val genres: @RawValue List<Genre>? = null,
        @SerializedName("overview")
        var description: String? = null
) : Parcelable