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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

class NetworkMoviesRepository @Inject constructor(
    private val moviesApiService: MovieApiService,
    private val movieResultMapper: MovieResultMapper,
    private val movieEntityMapper: MovieEntityMapper,
    private val moviesDatabase: MoviesDatabase,
) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val languageCode = Locale.getDefault().toLanguageTag()
        val regionCode = Locale.getDefault().country

        val nowPlayingMoviesResponse = try {
            moviesApiService.getNowPlayingMovies(
                apiKey = TmdbApi.API_KEY,
                language = languageCode,
                region = regionCode,
            )
        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch now playing movies.")
            throw GetNowPlayingMoviesException("Failed to fetch now playing movies.", e)
        }

        if (nowPlayingMoviesResponse.results.isEmpty()) {
            Timber.w("Received an empty list of now playing movies.")
            return@withContext emptyList()
        }

        val domainMovies: List<Movie> = nowPlayingMoviesResponse.results.map {
            movieResultMapper.fromRemote(it)
        }

        moviesDatabase.withTransaction {
            val moviesDao = moviesDatabase.moviesDao()
            try {
                moviesDao.deleteMovieResults()
                moviesDao.deleteNowPlayingMovieResponse()

                val movieEntities = domainMovies.map { movieEntityMapper.toLocal(it) }
                moviesDao.insertMovieResults(movieEntities)

                val responseEntity = NowPlayingMovieResponseEntity(
                    page = nowPlayingMoviesResponse.page,
                    totalPages = nowPlayingMoviesResponse.totalPages,
                    totalResults = nowPlayingMoviesResponse.totalResults
                )
                moviesDao.insertNowPlayingMovieResponse(responseEntity)
            } catch (e: Exception) {
                Timber.e(e, "Failed to save data to the local database.")
                throw SaveToDbException("Failed to save now playing movies.", e)
            }
        }

        domainMovies
    }

    class GetNowPlayingMoviesException(message: String, cause: Throwable) :
        RuntimeException(message, cause)

    class SaveToDbException(message: String, cause: Throwable) :
        RuntimeException(message, cause)
}