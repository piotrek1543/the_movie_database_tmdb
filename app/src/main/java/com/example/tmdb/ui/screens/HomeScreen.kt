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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb.MoviesViewModel
import com.example.tmdb.ui.theme.TMDBTheme
import com.example.tmdb.data.Constants
import com.example.tmdb.data.api.model.Results
import com.example.tmdb.domain.model.NowPlayingMovie

@Composable
fun HomeScreen(
    uiState: State<MoviesViewModel.MoviesUiState>,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (val moviesUiState = uiState.value) {
        is MoviesViewModel.MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MoviesViewModel.MoviesUiState.Success -> ResultsScreen(
            moviesUiState.movies, contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )

        is MoviesViewModel.MoviesUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
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
    results: List<NowPlayingMovie>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(integerResource(R.integer.movie_list_grid_size)),
        contentPadding = contentPadding,
    ) {
        items(items = results, key = { result -> result.id }) { result ->
            MovieImage(result.posterPath)
        }
    }
}

@Suppress("unused")
@Composable
private fun MovieDetailsScreen(
    result: Results,
    modifier: Modifier
) {
    Column {
        MovieImage(result.backdropPath)
        Column(
            modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Description(
                stringResource = stringResource(R.string.description_title),
                result.title,
            )
            Description(
                stringResource(R.string.description_original_title),
                result.originalTitle,
            )
            Description(
                stringResource(R.string.description_overview),
                result.overview,
            )
            Description(
                stringResource(R.string.description_release_date),
                result.releaseDate,
            )
            Description(
                stringResource(R.string.description_original_language),
                result.originalLanguage,
            )
            Description(
                stringResource(R.string.description_popularity),
                result.popularity.toString(),
            )
            Description(
                stringResource(R.string.description_vote_average),
                result.voteAverage.toString(),
            )
            Description(
                stringResource(R.string.description_vote_count),
                result.voteCount.toString(),
            )
        }
    }
}

@Composable
private fun Description(stringResource: String, title: String?) {
    Text(text = "$stringResource: $title")
}

@Composable
private fun MovieImage(imagePath: String? = null) {
    val fullPath = Constants.IMAGE_URL + imagePath
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(fullPath)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.description_backdrop_image),
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