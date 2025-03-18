package com.example.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    /**
     * Inserts a list of movie results into the database.
     *
     * @param movies The list of MovieResultEntity to insert.
     *
     * We use `OnConflictStrategy.REPLACE` to ensure that if a movie with the same primary key
     * already exists, it will be replaced with the new data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieResults(movies: List<MovieResultEntity>)

    /**
     * Retrieves a list of movie results from the database based on the specified list type.
     *
     * @param listType The type of the list to retrieve (e.g., "now_playing", "popular").
     * @return A Flow emitting a list of MovieResultEntity.
     *
     * Having a default parameter like this can be convenient for calls that don't need to
     * specify the list type, but it might hide the required nature of the parameter in some cases.
     *
     * Consider using an enum for the `listType` parameter to avoid errors due to typos.
     */
    @Query("SELECT * FROM movie_results WHERE list_type = :listType")
    fun getMovieResults(listType: String = "now_playing"): Flow<List<MovieResultEntity>>

    /**
     * Deletes movie results from the database based on the specified list type.
     *
     * @param listType The type of the list to delete (e.g., "now_playing", "popular").
     *
     * Similar to `getMovieResults`, consider using an enum for `listType`.
     */
    @Query("DELETE FROM movie_results WHERE list_type = :listType")
    suspend fun deleteMovieResults(listType: String = "now_playing")

    /**
     * Inserts a NowPlayingMovieResponse into the database.
     *
     * @param nowPlayingMovieResponse The NowPlayingMovieResponseEntity to insert.
     *
     * We use `OnConflictStrategy.REPLACE` to ensure that if a response with the same primary key
     * already exists, it will be replaced with the new data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovieResponse(nowPlayingMovieResponse: NowPlayingMovieResponseEntity)

    /**
     * Retrieves the NowPlayingMovieResponse from the database.
     *
     * @return A Flow emitting a single NowPlayingMovieResponseEntity or null if no data is found.
     *
     * We assume that there is only one "now playing" response and that it is stored
     * with id=1.
     *
     * Using a Flow here is a good practice, even if there's a single result, as it allows the
     * UI to react to changes in the data.
     */
    @Query("SELECT * FROM now_playing_movie_response WHERE id = 1")
    fun getNowPlayingMovieResponse(): Flow<NowPlayingMovieResponseEntity?>

    /**
     * Deletes the NowPlayingMovieResponse from the database.
     */
    @Query("DELETE FROM now_playing_movie_response")
    suspend fun deleteNowPlayingMovieResponse()
}