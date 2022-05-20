package com.example.moviesapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.common.Loading
import com.example.moviesapp.common.NoInternetState
import com.example.moviesapp.common.*
import com.example.moviesapp.core.movie.Movie
import com.example.moviesapp.data.common.FIRSTPAGE
import com.example.moviesapp.data.common.LASTPAGE
import com.example.moviesapp.databinding.FragmentMoviesBinding
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(),CellClickListener{
    private lateinit var binding: FragmentMoviesBinding
    private val viewModelMovie: MovieListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
       binding.viewmodel = viewModelMovie

        return binding.root
        //return inflater.inflate( R.layout.fragment_movies, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToData()
        setupList()

        //viewModel.getAll()
        var id = FIRSTPAGE
        viewModelMovie.getAll(id)

        btnAdd.setOnClickListener {
            id = nextPage(id)
            viewModelMovie.getAll(id)
        }

        btnMinus.setOnClickListener {
            id = prevPage(id)
            viewModelMovie.getAll(id)
        }
    }

    private fun subscribeToData() {
        viewModelMovie.viewState.observe(viewLifecycleOwner, { viewState ->
            when (viewState) {
                is Loading -> emptyView.visibility = View.VISIBLE
                is Success -> {
                    if (viewState.data.isNotEmpty()) {
                        emptyView.visibility = View.GONE
                        showMoviesList(viewState.data)
                    }
                }
                is Error -> {
                    Toast.makeText(context, viewState.error.message, Toast.LENGTH_SHORT).show()
                }
                is NoInternetState -> {
                    Toast.makeText(context, "Offline", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupList() {
        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        val linearLayoutManager = LinearLayoutManager(context)
        rvWorkers.layoutManager = linearLayoutManager
        rvWorkers.addItemDecoration(divider)
        rvWorkers.adapter = GenericAdapter(this)
    }

    private fun showMoviesList(data: List<Movie>) {
        val adapter = GenericAdapter(this)
        rvWorkers.adapter = adapter
        adapter.setItems(data)
    }

    private fun nextPage(id: Int): Int{
        return if(id == LASTPAGE) LASTPAGE
        else id+1
    }

    private fun prevPage(id: Int): Int{
        return if(id == FIRSTPAGE) FIRSTPAGE
        else id-1
    }
    override fun onCellClickListener(movie: Movie) {
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
       findNavController().navigate(R.id.action_navigation_movie_to_navigation_detailsmovie,bundle)
    }

}