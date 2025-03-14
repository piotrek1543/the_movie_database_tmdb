package com.example.tmdb.domain.model

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class NowPlayingMovie(
    val voteCount: Int,
    val id: Int,
    val hasVideo: Boolean,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String?,
    val isAdult: Boolean,
    val overview: String,
    val releaseDate: LocalDate?,
) {
    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

        /**
         * Parses a date string into a LocalDate object.
         *
         * @param dateString The date string to parse.
         * @return The parsed LocalDate, or null if the string is blank or cannot be parsed.
         */
        fun parseDate(dateString: String?): LocalDate? {
            if (dateString.isNullOrBlank()) {
                return null
            }
            return try {
                LocalDate.parse(dateString, dateFormatter)
            } catch (e: DateTimeParseException) {
                Timber.e(e, "Error parsing date: $dateString")
                null
            }
        }
    }
}
