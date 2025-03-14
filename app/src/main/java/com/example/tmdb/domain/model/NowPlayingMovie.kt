package com.example.tmdb.domain.model

data class NowPlayingMovie(
    val voteCount: Int? = null,
    val id: Int,
    val video: Boolean? = null,
    val voteAverage: Float? = null,
    val title: String? = null,
    val popularity: Float? = null,
    val posterPath: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val genreIds: List<Int>? = null,
    val backdropPath: String? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
)