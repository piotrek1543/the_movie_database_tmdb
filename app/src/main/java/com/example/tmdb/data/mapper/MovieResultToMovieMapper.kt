package com.example.tmdb.data.mapper

import com.example.tmdb.data.api.model.MovieResult
import com.example.tmdb.domain.model.Movie

/**
 * Mapper to transform a [MovieResult] (from the remote API) to a [Movie] domain model.
 */
class MovieResultToMovieMapper : RemoteMapper<MovieResult, Movie> {

    override fun fromRemote(remote: MovieResult): Movie = Movie(
        voteCount = remote.voteCount,
        id = remote.id,
        hasVideo = remote.video,
        voteAverage = remote.voteAverage,
        title = remote.title,
        popularity = remote.popularity,
        posterPath = remote.posterPath,
        originalLanguage = remote.originalLanguage,
        originalTitle = remote.originalTitle,
        genreIds = remote.genreIds,
        backdropPath = remote.backdropPath,
        isAdult = remote.adult,
        overview = remote.overview,
        releaseDate = remote.releaseDate,
    )
}

