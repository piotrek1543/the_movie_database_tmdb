package com.example.tmdb.data.mapper

import com.example.tmdb.data.api.model.MovieResult
import com.example.tmdb.domain.model.NowPlayingMovie


fun MovieResult.mapToDomain(): NowPlayingMovie = NowPlayingMovie(
    voteCount = voteCount,
    id = id,
    video = video,
    voteAverage = voteAverage,
    title = title,
    popularity = popularity,
    posterPath = posterPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    genreIds = genreIds,
    backdropPath = backdropPath,
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
)