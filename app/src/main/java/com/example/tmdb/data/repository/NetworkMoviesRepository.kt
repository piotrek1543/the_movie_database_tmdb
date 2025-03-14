package com.example.tmdb.data.repository

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.model.NowPlayingMovieResponse
import com.example.tmdb.data.mapper.mapToDomain
import com.example.tmdb.domain.model.NowPlayingMovie
import com.example.tmdb.domain.repository.MoviesRepository
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
) : MoviesRepository {

    override suspend fun getMovies(): List<NowPlayingMovie> {
        // 1. Handle API call errors and empty responses gracefully.
        val response: Response<NowPlayingMovieResponse> = try {
            moviesApiService.getNowPlayingMovies(BuildConfig.API_KEY)
        } catch (e: Exception) {
            Timber.e(e, "Error fetching now playing movies")
            return emptyList()
        }

        // 2. Check if the API call was successful.
        if (!response.isSuccessful) {
            Timber.e("API call failed with code: ${response.code()}")
            return emptyList()
        }

        // 3. Check if the response body is not null.
        val nowPlayingMovieResponse: NowPlayingMovieResponse = response.body() ?: run {
            Timber.e("Response body is null")
            return emptyList()
        }

        // 4. Check if results is not null or empty.
        val results = nowPlayingMovieResponse.results
        if (results.isNullOrEmpty()) {
            Timber.w("No movies found in the response")
            return emptyList()
        }

        // 5. Make the mapping in a val.
        return results.map { it.mapToDomain() }
    }
}
