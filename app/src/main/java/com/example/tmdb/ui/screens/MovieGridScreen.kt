package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.domain.model.Movie
import com.example.tmdb.ui.theme.TMDBTheme
import java.time.LocalDate


/**
 * The home screen displaying a grid of movie results.
 */
@Composable
fun MovieGridScreen(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalSpacing: Dp = 0.dp,
    verticalSpacing: Dp = 0.dp
) {
    LazyVerticalGrid(
        modifier = modifier.padding(contentPadding),
        columns = GridCells.Fixed(integerResource(R.integer.movie_list_grid_size)),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
    ) {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            MoviePicture(imagePath = movie.posterPath)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieGridScreenPreview() {
    TMDBTheme {
        val exampleMovies = listOf(
            Movie(
                id = 950396,
                posterPath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
                title = "The Gorge",
                voteAverage = 7.8,
                voteCount = 1916,
                hasVideo = false,
                popularity = 27.269,
                originalLanguage = "en",
                originalTitle = "The Gorge",
                genreIds = emptyList(),
                backdropPath = null,
                isAdult = false,
                overview = "",
                releaseDate = LocalDate.parse("2025-02-13")
            ),
            Movie(
                id = 1126166,
                posterPath = "/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg",
                title = "Flight Risk",
                voteAverage = 6.1,
                voteCount = 470,
                hasVideo = false,
                popularity = 25.919,
                originalLanguage = "en",
                originalTitle = "Flight Risk",
                genreIds = emptyList(),
                backdropPath = null,
                isAdult = false,
                overview = "",
                releaseDate = LocalDate.parse("2025-01-22")
            ),
            Movie(
                id = 1013482,
                posterPath = "/z2yAWt1aAvhxap9mdjlQhXiEJT4.jpg",
                title = "Borderline",
                voteAverage = 5.5,
                voteCount = 13,
                hasVideo = false,
                popularity = 25.005,
                originalLanguage = "en",
                originalTitle = "Borderline",
                genreIds = emptyList(),
                backdropPath = null,
                isAdult = false,
                overview = "",
                releaseDate = LocalDate.parse("2025-03-14")
            ),
            Movie(
                id = 1013601,
                posterPath = "/95KmR0xNuZZ6DNESDwLKWGIBvMg.jpg",
                title = "The Alto Knights",
                voteAverage = 5.5,
                voteCount = 4,
                hasVideo = false,
                popularity = 23.418,
                originalLanguage = "en",
                originalTitle = "The Alto Knights",
                genreIds = emptyList(),
                backdropPath = null,
                isAdult = false,
                overview = "",
                releaseDate = LocalDate.parse("2025-03-14")
            ),
        )
        MovieGridScreen(
            movies = exampleMovies,
            horizontalSpacing = 8.dp,
            verticalSpacing = 8.dp
        )
    }
}