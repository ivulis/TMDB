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
    // Binding object instance with access to the views in the fragment_movie_list.xml layout
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater)
        viewModel.getMovieList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = MovieListAdapter(MovieListener { movie ->
            viewModel.onMovieClicked(movie)
            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
        })
        return binding.root
    }
}
