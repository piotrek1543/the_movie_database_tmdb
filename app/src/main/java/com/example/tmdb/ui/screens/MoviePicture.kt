package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb.R
import com.example.tmdb.data.api.TmdbApi
import com.example.tmdb.ui.theme.TMDBTheme

@Composable
fun MoviePicture(imagePath: String?) {
    val context = LocalContext.current

    // 1. Handle Missing or Invalid Image Path More Gracefully
    if (imagePath.isNullOrBlank()) {
        // Display a placeholder image when imagePath is null or blank.
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.ic_broken_image) // Use a no-image placeholder.
                .build(),
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_broken_image), //Error is the same as the no image
            contentDescription = stringResource(R.string.description_no_movie_picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        return
    }

    // 2. Build the Full Image URL Only Once
    val fullImagePath = TmdbApi.IMAGE_BASE_URL + imagePath

    // 3.  Use remember for the ImageRequest
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .data(fullImagePath)
            .crossfade(true)
            .build()
    }

    AsyncImage(
        model = imageRequest,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.description_movie_picture),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun MoviePicturePreview() {
    TMDBTheme {
        MoviePicture(imagePath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg")
    }
}