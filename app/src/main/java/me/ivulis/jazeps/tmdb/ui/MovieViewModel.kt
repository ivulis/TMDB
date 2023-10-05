package me.ivulis.jazeps.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.ivulis.jazeps.tmdb.data.MovieData
import me.ivulis.jazeps.tmdb.model.Movie

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun getMovieList() {
        _movies.value = MovieData.getMovieData()
    }
}
