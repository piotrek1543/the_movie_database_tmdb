package com.example.tmdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.domain.model.NowPlayingMovie
import com.example.tmdb.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _moviesUiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)

    /**
     * Public immutable state flow to expose the UI state to the UI.
     */
    val moviesUiState: StateFlow<MoviesUiState> = _moviesUiState.asStateFlow()

    /**
     * Initialize the view model by fetching movies immediately.
     */
    init {
        fetchMovies()
    }

    /**
     * Fetches movies information from the Movies API and updates the UI state.
     */
    fun fetchMovies() {
        viewModelScope.launch {
            _moviesUiState.update { MoviesUiState.Loading }
            _moviesUiState.update {
                try {
                    val movies = getNowPlayingMoviesUseCase()
                    MoviesUiState.Success(movies)
                } catch (e: IOException) {
                    MoviesUiState.Error(e.message ?: "Network error")
                } catch (e: HttpException) {
                    MoviesUiState.Error(e.message ?: "HTTP error")
                } catch (e: Exception) {
                    MoviesUiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    /**
     * UI state for the Movies screen.
     */
    sealed interface MoviesUiState {
        data class Success(val movies: List<NowPlayingMovie>) : MoviesUiState
        data class Error(val message: String) : MoviesUiState
        data object Loading : MoviesUiState
    }
}