package com.example.tmdb.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb.R
import com.example.tmdb.data.api.TmdbApi


@Composable
fun MovieImage(imagePath: String? = null) {
    val fullPath = TmdbApi.IMAGE_BASE_URL + imagePath
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

