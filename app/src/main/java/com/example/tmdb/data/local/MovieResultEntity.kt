package com.example.tmdb.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity for storing individual movie results
@Entity(tableName = "movie_results")
data class MovieResultEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    val title: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "has_video") val hasVideo: Boolean,
    @ColumnInfo(name = "genre_ids") val genreIds: String,
    // Additional fields (optional, if you need to track them):
    @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis(),
    //@ColumnInfo(name = "list_type") val listType: String = "now_playing",
)

//Entity for storing a list of now playing movies
@Entity(tableName = "now_playing_movie_response")
data class NowPlayingMovieResponseEntity(
    @PrimaryKey val id: Int = 1, // We set a fixed ID because there will only be one NowPlayingMovieResponse list
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val timestamp: Long = System.currentTimeMillis()// When the data was fetched
)