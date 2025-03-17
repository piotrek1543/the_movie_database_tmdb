package com.example.tmdb.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(private val gson: Gson) {
    @TypeConverter
    fun fromMovieResultList(movieResults: List<MovieResultEntity>): String {
        return gson.toJson(movieResults)
    }

    @TypeConverter
    fun toMovieResultList(movieResultListString: String): List<MovieResultEntity> {
        return gson.fromJson(
            movieResultListString,
            Array<MovieResultEntity>::class.java
        ).toList()
    }
}