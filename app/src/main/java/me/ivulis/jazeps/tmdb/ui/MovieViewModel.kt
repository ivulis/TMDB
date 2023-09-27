package me.ivulis.jazeps.tmdb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.model.Movie
import me.ivulis.jazeps.tmdb.model.MovieDetails
import me.ivulis.jazeps.tmdb.network.MovieApi

class MovieViewModel : ViewModel() {

    private val _movieId = MutableLiveData<String>()
    val movieId: LiveData<String> = _movieId

    private val _status = MutableStateFlow<MovieApiStatus>(MovieApiStatus.START)
    val status: StateFlow<MovieApiStatus> = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private var movieHistory: MutableList<MovieDetails> = mutableListOf()

    private val _movie = MutableLiveData<MovieDetails>()
    var movie: LiveData<MovieDetails> = _movie

    private var similarMoviesHistory: MutableList<List<Movie>> = mutableListOf()

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    private val _similarMoviesStatus = MutableStateFlow<MovieApiStatus>(MovieApiStatus.START)
    val similarMoviesStatus: StateFlow<MovieApiStatus> = _similarMoviesStatus

    fun getMovieList() {
        viewModelScope.launch {
            Log.d("LIST", "LOADING")
            _status.emit(MovieApiStatus.LOADING)
            MovieApi.retrofitService.getPopularMovies().onSuccess {
                Log.d("LIST", "SUCCESS")
                _movies.value = it.movies
                _status.emit(MovieApiStatus.SUCCESS)
            }.onFailure {
                Log.d("LIST", "ERROR")
                _status.emit(MovieApiStatus.ERROR(it.localizedMessage))
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        _movieId.value = movie.id.toString()
        movieId.value?.let { getMovieDetails(it) }
    }

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            _status.emit(MovieApiStatus.LOADING)
            MovieApi.retrofitService.getMovieDetails(movieId).onSuccess { currentMovie ->
                movieHistory.add(currentMovie)
                _movie.value = currentMovie
                _status.emit(MovieApiStatus.SUCCESS)
                getSimilarMovies(movieId)
            }.onFailure { error ->
                _status.emit(MovieApiStatus.ERROR(error.localizedMessage))
            }
        }
    }

    private fun getSimilarMovies(movieId: String) {
        viewModelScope.launch {
            _similarMoviesStatus.emit(MovieApiStatus.LOADING)
            MovieApi.retrofitService.getSimilarMovies(movieId).onSuccess { response ->
                similarMoviesHistory.add(response.movies)
                _similarMovies.value = response.movies
                _similarMoviesStatus.emit(MovieApiStatus.SUCCESS)
            }.onFailure { error ->
                similarMoviesHistory.add(listOf())
                _similarMovies.value = listOf()
                _similarMoviesStatus.emit(MovieApiStatus.ERROR(error.localizedMessage))
            }
        }
    }

    fun onNavigateUp() {
        if (movieHistory.isNotEmpty()) movieHistory.removeIf { it.id == movie.value?.id }
        if (similarMoviesHistory.isNotEmpty()) similarMoviesHistory.removeLast()
        if (movieHistory.isNotEmpty()) _movie.value = movieHistory.last()
        if (similarMoviesHistory.isNotEmpty()) _similarMovies.value = similarMoviesHistory.last()
    }
}

sealed class MovieApiStatus {
    data object START : MovieApiStatus()
    data object LOADING : MovieApiStatus()
    data object SUCCESS : MovieApiStatus()
    data class ERROR(val message: String?) : MovieApiStatus()
}
