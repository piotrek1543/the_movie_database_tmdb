package com.example.tmdb.data.repository

import androidx.room.withTransaction
import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.TmdbApi
import com.example.tmdb.data.local.MoviesDatabase
import com.example.tmdb.data.local.NowPlayingMovieResponseEntity
import com.example.tmdb.data.mapper.MovieEntityMapper
import com.example.tmdb.data.mapper.MovieResultMapper
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import java.util.Locale
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val movieResultMapper: MovieResultMapper,
    private val movieEntityMapper: MovieEntityMapper,
    private val moviesDatabase: MoviesDatabase,
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> {
        val languageCode = Locale.getDefault().toLanguageTag()
        val regionCode = Locale.getDefault().country

        val nowPlayingMoviesResponse = try {
            moviesApiService.getNowPlayingMovies(
                apiKey = TmdbApi.API_KEY,
                language = languageCode,
                region = regionCode,
            )
        } catch (e: Exception) {
            throw GetNowPlayingMoviesException("Failed to fetch now playing movies.", e)
        }

        if (nowPlayingMoviesResponse.results.isEmpty()) {
            return emptyList()
        }

        // 5. Use more descriptive variable names
        val domainMovies: List<Movie> = nowPlayingMoviesResponse.results.map {
            movieResultMapper.fromRemote(it)
        }

        // 6. Use transaction
        moviesDatabase.withTransaction {
            val moviesDao = moviesDatabase.moviesDao()
            moviesDao.deleteMovieResults()
            moviesDao.deleteNowPlayingMovieResponse()

            val movieEntities = domainMovies.map { movieEntityMapper.toLocal(it) }
            moviesDao.insertMovieResults(movieEntities)
            // 8. Use the class to define the columns.
            val responseEntity = NowPlayingMovieResponseEntity(
                page = nowPlayingMoviesResponse.page,
                totalPages = nowPlayingMoviesResponse.totalPages,
                totalResults = nowPlayingMoviesResponse.totalResults
            )
            moviesDao.insertNowPlayingMovieResponse(responseEntity)
        }

        return domainMovies
    }

    // 3. Custom exception
    class GetNowPlayingMoviesException(message: String, cause: Throwable) :
        RuntimeException(message, cause)
}
