package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.data.api.model.MovieResult


@Suppress("unused")
@Composable
private fun MovieDetailsScreen(
    result: MovieResult,
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
                result.releaseDate.toString(),
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
