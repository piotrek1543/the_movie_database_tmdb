package com.example.tmdb.domain.model

import java.time.LocalDate

data class Movie(
    val voteCount: Int,
    val id: Int,
    val hasVideo: Boolean,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String?,
    val isAdult: Boolean,
    val overview: String,
    val releaseDate: LocalDate?,
)
