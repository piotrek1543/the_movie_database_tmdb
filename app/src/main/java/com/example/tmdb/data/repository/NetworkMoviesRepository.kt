package com.example.tmdb.data.repository

import com.example.tmdb.data.api.TmdbApi
import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.model.NowPlayingMovieResponse
import com.example.tmdb.data.mapper.MovieResultToMovieMapper
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val mapper: MovieResultToMovieMapper,
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> {
        // 1. Handle API call errors and empty responses gracefully.
        val response: Response<NowPlayingMovieResponse> = try {
            moviesApiService.getNowPlayingMovies(TmdbApi.API_KEY)
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
        if (results.isEmpty()) {
            Timber.w("No movies found in the response")
            return emptyList()
        }

        // 5. Make the mapping in a val.
        return results.map { mapper.fromRemote(it) }
    }
}
