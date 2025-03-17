package com.example.tmdb.domain.model

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class Movie(
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
        private val dateFormatter: DateTimeFormatter by lazy {
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        }

        /**
         * Parses a date string into a LocalDate object.
         *
         * @param dateString The date string to parse.
         * @return The parsed LocalDate, or null if the string is blank or cannot be parsed.
         */
        fun parseDate(dateString: String?): LocalDate? {
            return dateString?.takeIf { it.isNotBlank() }?.let {
                try {
                    LocalDate.parse(it, dateFormatter)
                } catch (e: DateTimeParseException) {
                    Timber.e(e, "Error parsing date: $it")
                    null
                }
            }
        }

        /**
         * Extension function to parse a date string into a LocalDate object directly.
         *
         * @receiver The date string to parse.
         * @param dateFormatter The DateTimeFormatter used for parsing.
         * @return The parsed LocalDate, or null if the string is blank or cannot be parsed.
         */
        fun String?.safeParseDate(dateFormatter: DateTimeFormatter): LocalDate? =
            this?.takeIf { it.isNotBlank() }?.let { dateString ->
                try {
                    LocalDate.parse(dateString, dateFormatter)
                } catch (e: DateTimeParseException) {
                    Timber.e(e, "Error parsing date: $dateString")
                    null
                }
            }

        /**
         * Formats a LocalDate object into a date string.
         *
         * @param localDate The LocalDate object to format.
         * @param dateFormatter The DateTimeFormatter used for formatting.
         * @return The formatted date string, or null if the input LocalDate is null.
         */
        fun formatDate(
            localDate: LocalDate?,
            dateFormatter: DateTimeFormatter = this.dateFormatter
        ): String? {
            return localDate?.format(dateFormatter)
        }
    }
}
