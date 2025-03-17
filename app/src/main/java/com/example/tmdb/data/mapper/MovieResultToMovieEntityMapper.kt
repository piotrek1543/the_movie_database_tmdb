package com.example.tmdb.data.mapper

import com.example.tmdb.data.local.MovieResultEntity
import com.example.tmdb.domain.model.Movie
import java.time.Instant
import javax.inject.Inject

class MovieResultToMovieEntityMapper @Inject constructor() :
    LocalMapper<MovieResultEntity, Movie> {

    override fun fromLocal(local: MovieResultEntity): Movie = Movie(
        id = local.id,
        isAdult = local.adult,
        backdropPath = local.backdropPath,
        originalLanguage = local.originalLanguage,
        originalTitle = local.originalTitle,
        overview = local.overview,
        popularity = local.popularity,
        posterPath = local.posterPath,
        releaseDate = Movie.parseDate(local.releaseDate),
        title = local.title,
        voteAverage = local.voteAverage,
        voteCount = local.voteCount,
        hasVideo = local.hasVideo,
        genreIds = local.genreIds.split(",").map { it.toInt() },
    )

    override fun toLocal(domain: Movie): MovieResultEntity = MovieResultEntity(
        id = domain.id,
        adult = domain.isAdult,
        backdropPath = domain.backdropPath,
        originalLanguage = domain.originalLanguage,
        originalTitle = domain.originalTitle,
        overview = domain.overview,
        popularity = domain.popularity,
        posterPath = domain.posterPath,
        releaseDate = Movie.formatDate(domain.releaseDate),
        title = domain.title,
        voteAverage = domain.voteAverage,
        voteCount = domain.voteCount,
        hasVideo = domain.hasVideo,
        genreIds = domain.genreIds.joinToString(","),
        timestamp = Instant.now().toEpochMilli(),
    )
}
