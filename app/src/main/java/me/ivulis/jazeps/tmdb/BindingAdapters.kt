package me.ivulis.jazeps.tmdb

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.ivulis.jazeps.tmdb.model.Movie
import me.ivulis.jazeps.tmdb.ui.MovieApiStatus
import me.ivulis.jazeps.tmdb.ui.MovieListAdapter

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("progressBar")
fun bindProgressBar(statusProgressBar: ProgressBar, status: MovieApiStatus?) {
    when (status) {
        is MovieApiStatus.LOADING -> statusProgressBar.visibility = View.VISIBLE
        is MovieApiStatus.SUCCESS -> statusProgressBar.visibility = View.GONE
        is MovieApiStatus.ERROR -> statusProgressBar.visibility = View.GONE
        else -> {}
    }
}

@BindingAdapter("movieDetails")
fun bindMovieDetails(movieDetailsView: ScrollView, status: MovieApiStatus?) {
    when (status) {
        is MovieApiStatus.LOADING -> movieDetailsView.visibility = View.GONE
        is MovieApiStatus.SUCCESS -> movieDetailsView.visibility = View.VISIBLE
        is MovieApiStatus.ERROR -> movieDetailsView.visibility = View.GONE
        else -> {}
    }
}

//@BindingAdapter("similarMoviesView")
//fun bindSimilarMovies(similarMoviesView: LinearLayout, status: MovieApiStatus?) {
//    when(status) {
//        MovieApiStatus.LOADING -> {
//            similarMoviesView.visibility = View.GONE
//        }
//        MovieApiStatus.SUCCESS -> {
//            similarMoviesView.visibility = View.VISIBLE
//        }
//        MovieApiStatus.ERROR -> {
//            similarMoviesView.visibility = View.GONE
//        }
//        else -> {}
//    }
//}