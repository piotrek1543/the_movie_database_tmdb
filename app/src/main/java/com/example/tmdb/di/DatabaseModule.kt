package com.example.tmdb.di

import android.content.Context
import androidx.room.Room
import com.example.tmdb.data.local.Converters
import com.example.tmdb.data.local.MoviesDao
import com.example.tmdb.data.local.MoviesDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(
        @ApplicationContext context: Context,
        converters: Converters // Inject the Converters here
    ): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_database"
        )
            .addTypeConverter(converters)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}