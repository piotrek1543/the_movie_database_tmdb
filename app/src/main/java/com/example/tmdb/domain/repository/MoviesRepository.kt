package com.example.tmdb.domain.repository

import com.example.tmdb.domain.model.Movie

/**
 * Repository interface for fetching movie data.
 */
interface MoviesRepository {

    /**
     * Retrieves a list of movies that are currently playing in theaters.
     *
     * @return A  list of [Movie] objects on success, or an error on failure.
     */
    suspend fun getNowPlayingMovies(): List<Movie>

}
