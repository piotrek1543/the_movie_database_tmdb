package com.example.tmdb.data

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.api.MoviesApiService
import com.example.tmdb.data.model.MovieResponse

/**
 * Repository that fetch movies from Api.
 */
interface MoviesRepository {
    /** Fetches list of movies from Api */
    suspend fun getMovies(): MovieResponse
}

/**
 * Network Implementation of Repository that fetch movies from Api.
 */
class NetworkMoviesRepository(
    private val moviesApiService: MoviesApiService
) : MoviesRepository {
    /** Fetches Movies from Api*/
    override suspend fun getMovies(): MovieResponse = moviesApiService.getMovies(BuildConfig.API_KEY)
}