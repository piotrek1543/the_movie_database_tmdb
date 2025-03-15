package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.ui.theme.TMDBTheme

/**
 * The home screen displaying results.
 */
@Composable
fun ResultsScreen(
    results: List<Movie>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(integerResource(R.integer.movie_list_grid_size)),
        contentPadding = contentPadding,
    ) {
        items(items = results, key = { result -> result.id }) { result ->
            MoviePicture(result.posterPath)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    TMDBTheme {
        ResultsScreen(emptyList())
    }
}