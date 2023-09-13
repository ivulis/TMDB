package me.ivulis.jazeps.tmdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ivulis.jazeps.tmdb.databinding.MovieListItemBinding
import me.ivulis.jazeps.tmdb.model.Movie

class MovieListAdapter(private val clickListener: MovieListener) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(private var binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: MovieListener, movie: Movie) {
            binding.movie = movie
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.releaseDate == newItem.releaseDate
                    && oldItem.runtime == newItem.runtime
                    && oldItem.rating == newItem.rating
                    && oldItem.poster == newItem.poster
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            MovieListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(clickListener, movie)
    }
}

class MovieListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}
