package me.ivulis.jazeps.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import me.ivulis.jazeps.tmdb.R
import me.ivulis.jazeps.tmdb.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getMovieList()

        val binding = FragmentMovieListBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@MovieListFragment
            viewModel = this@MovieListFragment.viewModel
            popularMoviesRecyclerView.adapter = MovieListAdapter(horizontal = false, MovieListener { movie ->
                viewModel?.onMovieClicked(movie)
                findNavController().navigate(
                    R.id.action_movieListFragment_to_movieDetailFragment,
                    MovieDetailFragmentArgs(movie.title).toBundle()
                )
            })
        }

        return binding.root
    }
}
