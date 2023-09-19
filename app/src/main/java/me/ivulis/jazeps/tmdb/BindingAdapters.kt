package me.ivulis.jazeps.tmdb

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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

/**
 * Displays the [MovieApiStatus] of the network request in an image view.
 */
@BindingAdapter("progressBar")
fun bindProgressBar(statusProgressBar: ProgressBar, status: MovieApiStatus?) {
    when(status) {
        MovieApiStatus.LOADING -> {
            statusProgressBar.visibility = View.VISIBLE
        }
        MovieApiStatus.SUCCESS -> {
            statusProgressBar.visibility = View.GONE
        }
        MovieApiStatus.ERROR -> {
            statusProgressBar.visibility = View.GONE
        }
        else -> {}
    }
}

@BindingAdapter("error")
fun bindError(errorTextView: TextView, status: MovieApiStatus?) {
    when(status) {
        MovieApiStatus.LOADING -> {
            errorTextView.visibility = View.GONE
        }
        MovieApiStatus.SUCCESS -> {
            errorTextView.visibility = View.GONE
        }
        MovieApiStatus.ERROR -> {
            errorTextView.visibility = View.VISIBLE
            errorTextView.text = "Something went wrong"
        }
        else -> {}
    }
}
