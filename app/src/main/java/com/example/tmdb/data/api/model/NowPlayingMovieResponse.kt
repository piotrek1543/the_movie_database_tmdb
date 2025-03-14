package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NowPlayingMovieResponse(
    val results: List<MovieResult> = emptyList(),
    val page: Int = 1,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    val dates: DateRange? = null,
    @SerializedName("total_pages")
    val totalPages: Int = 1,
) : Parcelable