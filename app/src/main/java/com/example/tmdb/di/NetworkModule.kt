package com.example.tmdb.di

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.api.LocalDateAdapter
import com.example.tmdb.data.api.MovieApiService
import com.example.tmdb.data.api.TmdbApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an instance of OkHttpClient for network requests.
     * Configures logging for debugging purposes.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        // Add logging interceptor in debug mode.
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    /**
     * Provides an instance of [Retrofit] for network API calls.
     * Configures [GsonConverterFactory] and the base URL.
     *
     * @param okHttpClient The OkHttpClient instance to use for network requests.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(TmdbApi.BASE_URL)
            .build()
    }

    /**
     * Provides an instance of the [MovieApiService] for fetching movie data.
     *
     * @param retrofit The Retrofit instance to use for API calls.
     */
    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}

