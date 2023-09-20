package me.ivulis.jazeps.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import me.ivulis.jazeps.tmdb.R
import me.ivulis.jazeps.tmdb.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@MovieDetailFragment
            viewModel = this@MovieDetailFragment.viewModel
            similarMoviesRecyclerView.adapter = MovieListAdapter(horizontal = true, MovieListener { movie ->
                viewModel?.onMovieClicked(movie)
                findNavController().navigate(
                    R.id.action_movieDetailFragment_self,
                    MovieDetailFragmentArgs(movie.title).toBundle()
                )
            })
        }

        return binding.root
    }
}
