package com.example.tmdb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb.ui.theme.TMDBTheme
import com.example.tmdb.MoviesUiState
import com.example.tmdb.data.Constants
import com.example.tmdb.data.model.Results

@Composable
fun HomeScreen(
    uiState: MoviesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (uiState) {
        is MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MoviesUiState.Success -> ResultsScreen(
            uiState.results, contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )

        is MoviesUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * The home screen displaying results.
 */
@Composable
fun ResultsScreen(
    results: List<Results>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = contentPadding,
    ) {
        items(items = results, key = { result -> result.id }) { result ->
            Column {
                val imagePath = Constants.IMAGE_URL + result.backdropPath
                MovieImage(imagePath)
                Text(
                    text = result.toString(),
                    modifier = modifier.padding(all = 16.dp),
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun MovieImage(imagePath: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imagePath)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    TMDBTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    TMDBTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    TMDBTheme {
        ResultsScreen(emptyList())
    }
}