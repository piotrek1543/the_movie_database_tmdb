package com.example.tmdb.data.repository

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.api.MoviesApiService
import com.example.tmdb.data.model.MovieResponse
import javax.inject.Inject

/**
 * Network Implementation of Repository that fetch movies from Api.
 */
class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MoviesApiService
) : MoviesRepository {
    /** Fetches Movies from Api*/
    override suspend fun getMovies(): MovieResponse = moviesApiService.getMovies(BuildConfig.API_KEY)
}