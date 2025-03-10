package com.example.tmdb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.tmdb.data.MoviesRepository
import com.example.tmdb.data.model.Results
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface MoviesUiState {
    data class Success(val results: List<Results>) : MoviesUiState
    data object Error : MoviesUiState
    data object Loading : MoviesUiState
}

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    /**
     * Call getMovies() on init so we can display status immediately.
     */
    init {
        getMovies()
    }

    /**
     * Gets movies information from the Movies API Retrofit service and updates the
     * [Results] [List] [MutableList].
     */
    fun getMovies() {
        viewModelScope.launch {
            moviesUiState = MoviesUiState.Loading
            moviesUiState = try {
                val listResult = repository.getMovies()
                MoviesUiState.Success(
                    listResult.results ?: emptyList()
                )
            } catch (e: IOException) {
                MoviesUiState.Error
            } catch (e: HttpException) {
                MoviesUiState.Error
            }
        }
    }

    /**
     * Factory for [MoviesViewModel] that takes [MoviesRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TMDBApplication)
                val moviesRepository = application.container.moviesRepository
                MoviesViewModel(repository = moviesRepository)
            }
        }
    }
}