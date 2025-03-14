package com.example.tmdb.data

import com.example.tmdb.data.api.MoviesApiService
import com.example.tmdb.data.repository.MoviesRepository
import com.example.tmdb.data.repository.NetworkMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    fun provideMoviesApiService(): MoviesApiService {
        return  retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    fun provideMoviesRepository(moviesApiService: MoviesApiService): MoviesRepository {
        return NetworkMoviesRepository(moviesApiService)
    }

}