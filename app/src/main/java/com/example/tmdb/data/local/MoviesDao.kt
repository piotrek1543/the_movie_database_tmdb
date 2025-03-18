package com.example.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieResults(movies: List<MovieResultEntity>)

    @Query("SELECT * FROM movie_results WHERE list_type = :listType")
    fun getMovieResults(listType: String = "now_playing"): Flow<List<MovieResultEntity>>

    @Query("DELETE FROM movie_results WHERE list_type = :listType")
    suspend fun deleteMovieResults(listType: String = "now_playing")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovieResponse(nowPlayingMovieResponse: NowPlayingMovieResponseEntity)

    @Query("SELECT * FROM now_playing_movie_response WHERE id = 1")
    fun getNowPlayingMovieResponse(): Flow<NowPlayingMovieResponseEntity?>

    @Query("DELETE FROM now_playing_movie_response")
    suspend fun deleteNowPlayingMovieResponse()
}