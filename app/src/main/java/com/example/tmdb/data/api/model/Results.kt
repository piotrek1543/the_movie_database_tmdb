package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Results(
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    val id: Int,
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null,
    val title: String? = null,
    val popularity: Float? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
): Parcelable
