package com.example.tmdb.util


sealed class Result<out T> {
    /**
     * Represents a state where data is currently being loaded.
     */
    data object Loading : Result<Nothing>()

    /**
     * Represents a state where data has been successfully loaded.
     * @property data The loaded data.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents a state where an error occurred while loading data.
     * @property throwable The exception that caused the error.
     * @property message An optional error message.
     */
    data class Error(val throwable: Throwable, val message: String? = null) : Result<Nothing>()
}
