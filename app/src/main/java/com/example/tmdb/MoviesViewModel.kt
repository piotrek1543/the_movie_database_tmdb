package com.example.tmdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.tmdb.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
) : ViewModel() {

    /**
     * Private mutable state flow to hold the UI state of movies.
     */
    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)

    /**
     * Public immutable state flow to expose the UI state to the UI.
     */
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    /**
     * Initialize the view model by fetching movies immediately.
     */
    init {
        loadNowPlayingMovies()
    }

    /**
     * Fetches movies information from the Movies API and updates the UI state.
     */
    fun loadNowPlayingMovies() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update { MoviesUiState.Loading }
                    }

                    is Result.Success -> {
                        _uiState.update { MoviesUiState.Success(result.data) }
                    }

                    is Result.Error -> {
                        _uiState.update { MoviesUiState.Error(getErrorMessage(result.throwable)) }
                    }
                }
            }
        }
    }

    /**
     * Helper function to determine the appropriate error message based on the exception type.
     */
    private fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is IOException -> "Failed to connect to the network. Please check your connection."
            is HttpException -> "Failed to fetch data from the server. HTTP error ${throwable.code()}"
            else -> "An unexpected error occurred. Please try again later."
        }
    }

    /**
     * UI state for the Movies screen.
     */
    sealed interface MoviesUiState {
        data class Success(val movies: List<Movie>) : MoviesUiState
        data class Error(val message: String) : MoviesUiState
        data object Loading : MoviesUiState
    }
}