package com.example.tmdb.data.api

import com.example.tmdb.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Retrofit service object for creating api calls
 */
interface MoviesApiService {

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") apiKey: String?): MovieResponse
}