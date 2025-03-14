package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): List<Movie> = moviesRepository.getNowPlayingMovies()
}