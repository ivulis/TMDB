package me.ivulis.jazeps.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.R
import me.ivulis.jazeps.tmdb.databinding.FragmentMovieListBinding
import me.ivulis.jazeps.tmdb.extension.alert
import me.ivulis.jazeps.tmdb.extension.negativeButton
import me.ivulis.jazeps.tmdb.extension.positiveButton

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
            popularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel?.getMovieList()
                    }
                }
            })
        }

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
