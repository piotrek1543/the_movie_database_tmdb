package com.example.tmdb.data.api

import com.example.tmdb.BuildConfig

/**
 * Object containing constants related to the TMDB API.
 */
object TmdbApi {

    /**
     * The base URL for the TMDB API.
     */
    const val BASE_URL: String = "https://api.themoviedb.org/3/"

    /**
     * The base URL for images from the TMDB API.
     * Using 'w500' is recommended for general use to avoid extremely large image downloads.
     * More info: https://developer.themoviedb.org/docs/image-basics
     */
    const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"

    /**
     * The API key used for authenticating requests to the TMDB API.
     * It is not recommended to store API keys directly in the source code.
     * This is just an example, in a real application, it should be stored
     * securely, e.g., in environment variables or using the Android Secrets Gradle Plugin.
     */
    const val API_KEY: String = BuildConfig.API_KEY
}