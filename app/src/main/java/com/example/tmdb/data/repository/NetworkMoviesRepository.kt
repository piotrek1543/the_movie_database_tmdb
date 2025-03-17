package com.example.tmdb.data.repository

import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.TmdbApi
import com.example.tmdb.data.api.model.NowPlayingMovieResponse
import com.example.tmdb.data.mapper.MovieResultToMovieMapper
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val mapper: MovieResultToMovieMapper,
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> {
        val response: NowPlayingMovieResponse =
            moviesApiService.getNowPlayingMovies(TmdbApi.API_KEY)
        return response.results.map { mapper.fromRemote(it) }
    }
}
