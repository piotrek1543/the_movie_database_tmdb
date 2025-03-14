package com.example.tmdb.data.repository

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.api.MoviesApiService
import com.example.tmdb.data.mapper.mapToDomain
import com.example.tmdb.domain.model.NowPlayingMovie
import com.example.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MoviesApiService,
) : MoviesRepository {

    override suspend fun getMovies(): List<NowPlayingMovie> =
        moviesApiService.getMovies(BuildConfig.API_KEY)
            .results?.map { it.mapToDomain() } ?: emptyList()

}