package me.ivulis.jazeps.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.R
import me.ivulis.jazeps.tmdb.databinding.FragmentMovieListBinding
import me.ivulis.jazeps.tmdb.extension.alert
import me.ivulis.jazeps.tmdb.extension.negativeButton
import me.ivulis.jazeps.tmdb.extension.positiveButton

class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater)
        viewModel.getMovieList()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.adapter = MovieListAdapter(horizontal = false, MovieListener { movie ->
            viewModel.onMovieClicked(movie)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailFragment,
                MovieDetailFragmentArgs(movie.title).toBundle()
            )
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.status.collect { status ->
                when (status) {
                    is MovieApiStatus.ERROR -> {
                        alert {
                            setMessage(status.message)
                            positiveButton { viewModel.getMovieList() }
                            negativeButton { activity?.finish() }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
