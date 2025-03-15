package com.example.tmdb.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class DateRange(
    @SerializedName("maximum")
    val maximum: LocalDate,
    @SerializedName("minimum")
    val minimum: LocalDate
): Parcelable
