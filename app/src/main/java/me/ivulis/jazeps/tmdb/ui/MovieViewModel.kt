package me.ivulis.jazeps.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.model.Movie
import me.ivulis.jazeps.tmdb.network.MovieApi

class MovieViewModel : ViewModel() {

    private var pageNumber = 1

    private val _status = MutableStateFlow<MovieApiStatus>(MovieApiStatus.START)
    val status: StateFlow<MovieApiStatus> = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private var movieHistory: MutableList<Movie> = mutableListOf()

    private val _movieId = MutableLiveData<String>()
    val movieId: LiveData<String> = _movieId

    private val _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    private var similarMoviesHistory: MutableList<List<Movie>> = mutableListOf()

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    fun getMovieList() {
        viewModelScope.launch {
            _status.emit(MovieApiStatus.LOADING)
            MovieApi.retrofitService.getPopularMovies(pageNumber.toString()).onSuccess {
                pageNumber += 1
                if (_movies.value.isNullOrEmpty()) {
                    _movies.value = it.movies
                } else {
                    _movies.value = _movies.value!!.plus(it.movies)
                }
                _status.emit(MovieApiStatus.SUCCESS)
            }.onFailure {
                _status.emit(MovieApiStatus.ERROR(it.localizedMessage))
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        _movieId.value = movie.id
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
            MovieApi.retrofitService.getSimilarMovies(movieId).onSuccess { response ->
                similarMoviesHistory.add(response.movies)
                _similarMovies.value = response.movies
            }.onFailure {
                similarMoviesHistory.add(listOf())
                _similarMovies.value = listOf()
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
