package me.ivulis.jazeps.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.model.Movie
import me.ivulis.jazeps.tmdb.network.MovieApi

enum class MovieApiStatus {LOADING, ERROR, SUCCESS}

class MovieViewModel : ViewModel() {

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus> = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _movie = MutableLiveData<List<Movie>>()
    lateinit var movie: Movie

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    fun getMovieList() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                _movies.value = MovieApi.retrofitService.getPopularMovies().movies
                _status.value = MovieApiStatus.SUCCESS
            } catch (e: Exception) {
                _movies.value = listOf()
                _status.value = MovieApiStatus.ERROR
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        _movie.value = _movie.value?.plus(movie) ?: listOf(movie)
        if (_movie.value?.isEmpty() == false) {
            this.movie = _movie.value!!.last()
        }
//        _similarMovies.value = MovieData.getMovieData()
    }

    fun onNavigateUp() {
        _movie.value = _movie.value?.toMutableList()?.apply { removeLast() }?.toList()
        if (_movie.value?.isEmpty() == false) {
            this.movie = _movie.value!!.last()
        }
    }
}
