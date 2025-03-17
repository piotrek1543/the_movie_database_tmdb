package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.data.api.model.MovieResult


@Suppress("unused")
@Composable
fun MovieDetailsScreen(
    result: MovieResult,
    modifier: Modifier = Modifier // Make modifier optional and provide a default
) {
    Column(modifier = modifier.fillMaxWidth()) { //fill the width of the screen
        MoviePicture(imagePath = result.backdropPath)
        Column(
            Modifier
                .fillMaxWidth() //fill the width of the screen
                .padding(16.dp) // Use a single padding value for simplicity
        ) {
            // Use a loop to display the descriptions, it's more scalable and readable
            listOf(
                R.string.description_title to result.title,
                R.string.description_original_title to result.originalTitle,
                R.string.description_overview to result.overview,
                R.string.description_release_date to result.releaseDate?.toString(), //Safe call
                R.string.description_original_language to result.originalLanguage,
                R.string.description_popularity to result.popularity.toString(),
                R.string.description_vote_average to result.voteAverage.toString(),
                R.string.description_vote_count to result.voteCount.toString(),
            ).forEach { (stringResId, value) ->
                if(!value.isNullOrEmpty()) { // Do not display fields that don't contain data
                    Description(
                        title = stringResource(id = stringResId), //pass the id of the string
                        value = value,
                    )
                }
            }
        }
    }
}

@Composable
private fun Description(title: String, value: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp)) // Add some spacing between items
    }
}