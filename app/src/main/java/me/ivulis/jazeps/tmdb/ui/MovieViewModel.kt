package me.ivulis.jazeps.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.ivulis.jazeps.tmdb.data.MovieData
import me.ivulis.jazeps.tmdb.model.Movie

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    fun getMovieList() {
        _movies.value = MovieData.getMovieData()
    }

    fun onMovieTapped(movie: Movie) {
        _movie.value = movie
        _similarMovies.value = MovieData.getMovieData()
    }
}
