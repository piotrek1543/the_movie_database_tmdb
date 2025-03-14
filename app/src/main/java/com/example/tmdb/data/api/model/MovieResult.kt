package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResult(
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    val id: Int,
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0f,
    val title: String = "",
    val popularity: Float = 0f,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
) : Parcelable

