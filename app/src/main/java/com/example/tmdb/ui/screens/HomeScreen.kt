package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is MoviesViewModel.MoviesUiState.Loading -> LoadingScreen()
            is MoviesViewModel.MoviesUiState.Success ->
                when {
                    uiState.movies.isNotEmpty() -> MovieGridScreen(
                        movies = uiState.movies,
                        modifier = Modifier.fillMaxWidth()
                    )

                    else -> {
                        EmptyScreen()
                    }
                }

            is MoviesViewModel.MoviesUiState.Error -> ErrorScreen(retryAction = retryAction)
        }
    }
}
