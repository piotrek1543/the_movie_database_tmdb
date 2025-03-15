package com.example.tmdb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.TMDBTheme

/**
 * A screen that displays a loading indicator.
 *
 * This composable displays a central loading animation to indicate that content is being loaded.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadingIndicator(modifier = Modifier.size(200.dp))
    }
}

/**
 * A reusable composable for displaying a loading animation.
 *
 * @param modifier Modifier for styling the loading indicator.
 */
@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
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
fun LoadingIndicatorPreview() {
    TMDBTheme {
        LoadingIndicator(modifier = Modifier.size(200.dp))
    }
}
