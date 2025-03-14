package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import com.example.tmdb.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    /**
     * Retrieves a list of now playing movies.
     *
     * This use case fetches the now playing movies from the repository. It wraps the result in a
     * [Result] sealed class to handle both success and failure scenarios.
     *
     * @return A [Flow] emitting a [Result] containing either a list of [Movie] or an error message.
     */
    operator fun invoke(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        try {
            val movies = moviesRepository.getNowPlayingMovies()
            emit(Result.Success(movies))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}