package me.ivulis.jazeps.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.ivulis.jazeps.tmdb.data.MovieData
import me.ivulis.jazeps.tmdb.model.Movie

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _movie = MutableLiveData<List<Movie>>()
    lateinit var movie: Movie

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    fun getMovieList() {
        _movies.value = MovieData.getMovieData()
    }

    fun onMovieClicked(movie: Movie) {
        _movie.value = _movie.value?.plus(movie) ?: listOf(movie)
        if (_movie.value?.isEmpty() == false) {
            this.movie = _movie.value!!.last()
        }
        _similarMovies.value = MovieData.getMovieData()
    }

    fun onNavigateUp() {
        _movie.value = _movie.value?.toMutableList()?.apply { removeLast() }?.toList()
        if (_movie.value?.isEmpty() == false) {
            this.movie = _movie.value!!.last()
        }
    }
}
