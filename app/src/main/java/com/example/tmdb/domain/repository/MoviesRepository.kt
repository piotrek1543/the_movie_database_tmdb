package com.example.tmdb.domain.repository

import com.example.tmdb.domain.model.NowPlayingMovie

interface MoviesRepository {

    suspend fun getMovies(): List<NowPlayingMovie>

}

