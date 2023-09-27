package me.ivulis.jazeps.tmdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.ivulis.jazeps.tmdb.databinding.MovieListItemBinding
import me.ivulis.jazeps.tmdb.databinding.SimilarMovieListItemBinding
import me.ivulis.jazeps.tmdb.model.Movie

class MovieListAdapter(
    private val horizontal: Boolean = false,
    private val clickListener: MovieListener
) :
    ListAdapter<Movie, ViewHolder>(DiffCallback) {

    class MovieViewHolder(private var binding: MovieListItemBinding) : ViewHolder(binding.root) {
        fun bind(clickListener: MovieListener, movie: Movie) {
            binding.apply {
                this.movie = movie
                this.clickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }

    class SimilarMovieViewHolder(private var binding: SimilarMovieListItemBinding) :
        ViewHolder(binding.root) {
        fun bind(clickListener: MovieListener, movie: Movie) {
            binding.apply {
                this.movie = movie
                this.clickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): SimilarMovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SimilarMovieListItemBinding.inflate(layoutInflater, parent, false)
                return SimilarMovieViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.releaseDate == newItem.releaseDate
                    && oldItem.rating == newItem.rating
                    && oldItem.poster == newItem.poster
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (horizontal) SimilarMovieViewHolder.from(parent)
        else MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                val movie = getItem(position)
                holder.bind(clickListener, movie)
            }

            is SimilarMovieViewHolder -> {
                val movie = getItem(position)
                holder.bind(clickListener, movie)
            }
        }
    }
}

class MovieListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}
