package com.example.tmdb.data.mapper

import com.example.tmdb.data.local.MovieResultEntity
import com.example.tmdb.domain.model.Movie
import timber.log.Timber
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() :
    LocalMapper<MovieResultEntity, Movie> {

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

    override fun fromLocal(local: MovieResultEntity): Movie = Movie(
        id = local.id,
        isAdult = local.adult,
        backdropPath = local.backdropPath,
        originalLanguage = local.originalLanguage,
        originalTitle = local.originalTitle,
        overview = local.overview,
        popularity = local.popularity,
        posterPath = local.posterPath,
        releaseDate = parseDate(local.releaseDate),
        title = local.title,
        voteAverage = local.voteAverage,
        voteCount = local.voteCount,
        hasVideo = local.hasVideo,
        genreIds = local.genreIds.parseGenreIds(),
    )

    override fun toLocal(domain: Movie): MovieResultEntity = MovieResultEntity(
        id = domain.id,
        adult = domain.isAdult,
        backdropPath = domain.backdropPath,
        originalLanguage = domain.originalLanguage,
        originalTitle = domain.originalTitle,
        overview = domain.overview,
        popularity = domain.popularity,
        posterPath = domain.posterPath,
        releaseDate = formatDate(domain.releaseDate),
        title = domain.title,
        voteAverage = domain.voteAverage,
        voteCount = domain.voteCount,
        hasVideo = domain.hasVideo,
        genreIds = domain.genreIds.joinToString(","),
        timestamp = Instant.now().toEpochMilli(),
    )

    private fun String.parseGenreIds(): List<Int> {
        return if (isBlank()) {
            emptyList()
        } else {
            split(",").mapNotNull { it.toIntOrNull() } // Use toIntOrNull to handle parsing errors
        }
    }
}
