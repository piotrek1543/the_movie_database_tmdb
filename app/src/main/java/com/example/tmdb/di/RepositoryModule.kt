package com.example.tmdb.di

import com.example.tmdb.data.repository.NetworkMoviesRepository
import com.example.tmdb.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provides an instance of the [MoviesRepository] for managing movie data.
     *
     * @param repository The NetworkMoviesRepository instance to use for fetching movie data.
     */
    @Binds
    @Singleton
    abstract fun bindMoviesRepository(repository: NetworkMoviesRepository): MoviesRepository
}