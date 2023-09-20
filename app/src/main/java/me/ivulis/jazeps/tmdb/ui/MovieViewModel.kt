package me.ivulis.jazeps.tmdb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.model.Movie
import me.ivulis.jazeps.tmdb.model.MovieDetails
import me.ivulis.jazeps.tmdb.network.MovieApi

enum class MovieApiStatus {LOADING, ERROR, SUCCESS}

class MovieViewModel : ViewModel() {

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus> = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private var movieHistory: MutableList<MovieDetails> = mutableListOf()

    private val _movie = MutableLiveData<MovieDetails>()
    var movie: LiveData<MovieDetails> = _movie

    private var similarMoviesHistory: MutableList<List<Movie>> = mutableListOf()

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
        viewModelScope.launch {
            try {
                val currentMovie = MovieApi.retrofitService.getMovieDetails(movie.id.toString())
                movieHistory.add(currentMovie)
                _movie.value = currentMovie

                try {
                    val currentSimilarMovies = MovieApi.retrofitService.getSimilarMovies(movie.id.toString()).movies
                    similarMoviesHistory.add(currentSimilarMovies)
                    _similarMovies.value = currentSimilarMovies
                } catch (e: Exception) {
                    similarMoviesHistory.add(listOf())
                    _similarMovies.value = listOf()
                    Log.d("MOVIEE ERROR", "${e.message}")
                }
            } catch (e: Exception) {
                Log.d("MOVIEE ERROR", "${e.message}")
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
