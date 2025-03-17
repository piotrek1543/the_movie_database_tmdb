package com.example.tmdb.data.repository

import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.TmdbApi
import com.example.tmdb.data.api.model.NowPlayingMovieResponse
import com.example.tmdb.data.local.MoviesDao
import com.example.tmdb.data.local.NowPlayingMovieResponseEntity
import com.example.tmdb.data.mapper.MovieResultToMovieEntityMapper
import com.example.tmdb.data.mapper.MovieResultToMovieMapper
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val mapper: MovieResultToMovieMapper,
    private val movieResultToMovieEntityMapper: MovieResultToMovieEntityMapper,
    private val moviesDao: MoviesDao,
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> {
        val nowPlayingMoviesResponse: NowPlayingMovieResponse =
            moviesApiService.getNowPlayingMovies(TmdbApi.API_KEY)

        val domainMovies = nowPlayingMoviesResponse.results.map { mapper.fromRemote(it) }

        moviesDao.clearMovieResults()
        moviesDao.clearNowPlayingMovieResponse()
        moviesDao.insertMovieResults(domainMovies.map { movieResultToMovieEntityMapper.toLocal(it) })
        moviesDao.insertNowPlayingMovieResponse(
            NowPlayingMovieResponseEntity(
            page = nowPlayingMoviesResponse.page,
            totalPages = nowPlayingMoviesResponse.totalPages,
            totalResults = nowPlayingMoviesResponse.totalResults)
        )

        return domainMovies
    }
}
