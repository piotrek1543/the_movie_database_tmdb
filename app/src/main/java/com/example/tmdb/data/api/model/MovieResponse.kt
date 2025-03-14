package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val results: List<Results>? = null,
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    val dates: Dates? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
): Parcelable