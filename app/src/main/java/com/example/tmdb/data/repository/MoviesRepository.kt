package com.example.tmdb.data.repository

import com.example.tmdb.data.model.MovieResponse

/**
 * Repository that fetch movies from Api.
 */
interface MoviesRepository {
    /** Fetches list of movies from Api */
    suspend fun getMovies(): MovieResponse
}

