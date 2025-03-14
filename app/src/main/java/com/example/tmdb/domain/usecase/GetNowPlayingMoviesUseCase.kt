package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.model.NowPlayingMovie
import com.example.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): List<NowPlayingMovie> = moviesRepository.getMovies()
}