package com.example.tmdb.data.api

import com.example.tmdb.data.api.model.NowPlayingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for interacting with the Movie API.
 */
interface MovieApiService {

    /**
     * Retrieves a list of movies currently playing in theaters.
     *
     * @param apiKey The API key for authentication. It's a mandatory parameter.
     * @param language The language to return the movies in. Default is "en-US"
     * @param page The page number to retrieve. Default is 1
     * @param region The region to return the movies from. Optional.
     * @return A [NowPlayingMovieResponse] object containing the list of movies.
     * @throws HttpException If the API request fails (e.g., 401 Unauthorized, 404 Not Found).
     * @throws IOException If there's a network-related issue.
     * @throws Exception For other exceptions that may occur.
     */
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null,
    ): NowPlayingMovieResponse
}