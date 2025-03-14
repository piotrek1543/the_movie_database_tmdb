package com.example.tmdb.data.mapper

import com.example.tmdb.data.api.model.MovieResult
import com.example.tmdb.domain.model.Movie


fun MovieResult.mapToDomain(): Movie = Movie(
    voteCount = voteCount,
    id = id,
    hasVideo = video,
    voteAverage = voteAverage.toDouble(),
    title = title,
    popularity = popularity.toDouble(),
    posterPath = posterPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    genreIds = genreIds,
    backdropPath = backdropPath,
    isAdult = adult,
    overview = overview,
    releaseDate = Movie.parseDate(releaseDate),
)