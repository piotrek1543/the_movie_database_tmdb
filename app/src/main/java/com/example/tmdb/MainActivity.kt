@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmdb.ui.screens.HomeScreen
import com.example.tmdb.ui.theme.TMDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBTheme {
                // Use a dedicated composable for the root of the app
                TMDBApp(moviesViewModel)
            }
        }
    }
}

@Composable
fun TMDBApp(moviesViewModel: MoviesViewModel) {
    // Use remember to avoid recomposing each time
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // Use collectAsStateWithLifecycle for lifecycle awareness
    val moviesUiState by moviesViewModel.uiState.collectAsStateWithLifecycle()

    TMDBAppContent(moviesUiState, moviesViewModel::loadNowPlayingMovies, scrollBehavior)
}

@Composable
fun TMDBAppContent(
    moviesUiState: MoviesViewModel.MoviesUiState,
    retryAction: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TMDBTopAppBar(scrollBehavior) },
    ) { innerPadding ->
        MainContent(innerPadding, moviesUiState, retryAction)
    }
}

@Composable
fun MainContent(
    innerPadding: PaddingValues,
    uiState: MoviesViewModel.MoviesUiState,
    retryAction: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeScreen(
                uiState = uiState,
                retryAction = retryAction,
            )
        }
    }
}

@Composable
fun TMDBTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
