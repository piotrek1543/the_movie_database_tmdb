package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.model.Movie
import com.example.tmdb.domain.repository.MoviesRepository
import com.example.tmdb.util.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class GetNowPlayingMoviesUseCaseTest {

    private val mockMoviesRepository: MoviesRepository = mock()
    private val useCase = GetNowPlayingMoviesUseCase(mockMoviesRepository)

    //region Test Data
    companion object {
        private val MOCK_MOVIE = Movie(
            id = 1,
            title = "Movie 1",
            voteCount = 1,
            hasVideo = false,
            voteAverage = 2.0,
            popularity = 10.0,
            posterPath = "",
            originalLanguage = "",
            originalTitle = "",
            genreIds = emptyList(),
            backdropPath = "",
            isAdult = false,
            overview = "",
            releaseDate = LocalDate.now()
        )
    }
    //endregion

    @Test
    fun invoke_returnsSuccessWithMovies_onSuccessfulFetch() = runTest {
        val expectedMovies = listOf(MOCK_MOVIE)
        whenever(mockMoviesRepository.getNowPlayingMovies()).thenReturn(expectedMovies)

        val results = useCase().take(2).toList()

        require(results[0] is Result.Loading)
        require(results[1] is Result.Success<List<Movie>>)

        assertEquals(expectedMovies, (results[1] as Result.Success<List<Movie>>).data)
    }

    @Test
    fun invoke_returnsError_onRepositoryException() = runTest {
        val expectedException = RuntimeException("Network error")
        whenever(mockMoviesRepository.getNowPlayingMovies()).thenThrow(expectedException)

        val result = useCase().first { it is Result.Error }

        require(result is Result.Error)
        assertEquals(expectedException, result.throwable)
    }

    @Test
    fun invoke_emitsLoadingFirst() = runTest {
        val expectedMovies = listOf(MOCK_MOVIE)
        whenever(mockMoviesRepository.getNowPlayingMovies()).thenReturn(expectedMovies)

        val results = useCase().take(2).toList()

        assertEquals(2, results.size)
        assertEquals(Result.Loading, results[0])
        require(results[1] is Result.Success<List<Movie>>)
    }
}
