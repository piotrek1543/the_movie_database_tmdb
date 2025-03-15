package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdb.MoviesViewModel

@Composable
fun HomeScreen(
    uiState: MoviesViewModel.MoviesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (uiState) {
        is MoviesViewModel.MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MoviesViewModel.MoviesUiState.Success -> MovieGridScreen(
            uiState.movies,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxWidth()
        )

        is MoviesViewModel.MoviesUiState.Error -> ErrorScreen(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}







