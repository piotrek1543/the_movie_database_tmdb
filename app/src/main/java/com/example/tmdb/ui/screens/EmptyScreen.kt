package com.example.tmdb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.TMDBTheme

/**
 * A screen to display when there is no content to show.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param message A message to display on the screen, defaults to a generic "No results found" message.
 */
@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.no_results_found)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = stringResource(R.string.connection_error_content_description),
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    TMDBTheme {
        EmptyScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenWithMessagePreview() {
    TMDBTheme {
        EmptyScreen(message = "No movies found for this search.")
    }
}