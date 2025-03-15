package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MovieResult(
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    val id: Int,
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    val title: String = "",
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val adult: Boolean = false,
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: LocalDate? = null,
) : Parcelable


