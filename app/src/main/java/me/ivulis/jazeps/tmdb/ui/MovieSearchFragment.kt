package me.ivulis.jazeps.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.ivulis.jazeps.tmdb.R
import me.ivulis.jazeps.tmdb.databinding.FragmentMovieSearchBinding

class MovieSearchFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMovieSearchBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@MovieSearchFragment
            viewModel = this@MovieSearchFragment.viewModel
            movieSearchView.setOnQueryTextListener(object : OnQueryTextListener{
                private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

                private var searchJob: Job? = null

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        newText?.let { keyword ->
                            delay(500)
                            if (keyword.isEmpty()) {
                                viewModel?.resetSearch()
                            } else {
                                viewModel?.searchKeyword = keyword
                                viewModel?.getSearchedMoviesList()
                            }
                        }
                    }
                    return true
                }
            })
            searchMoviesRecyclerView.adapter = MovieListAdapter(horizontal = false, MovieListener { movie ->
                viewModel?.onMovieClicked(movie)
                findNavController().navigate(
                    R.id.action_movieSearchFragment_to_movieDetailFragment,
                    MovieDetailFragmentArgs(movie.title).toBundle()
                )
            })
            searchMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState== RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel?.getMoreSearchedMoviesList()
                    }
                }
            })
        }

        return binding.root
    }
}
