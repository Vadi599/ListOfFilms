package com.example.listoffilms.fragments.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listoffilms.adapter.MoviesListAdapter
import com.example.listoffilms.databinding.FragmentFilmsBinding
import com.example.listoffilms.model.Movie

class FilmsFragment : Fragment(), FilmsView {

    private lateinit var binding: FragmentFilmsBinding

    lateinit var adapter: MoviesListAdapter

    lateinit var presenter: FilmsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmsBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        val view = binding.root
        presenter = FilmsPresenter()
        val listOfFilms = presenter.listOfFilms
        adapter = MoviesListAdapter(listOfFilms)
        binding.rvMovies.adapter = adapter
        sortByDescending()
        sortByAscending()
        return view
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.setResultsList(movies)
    }

    private fun sortByDescending() {
        val listOfFilms = presenter.listOfFilms
        val sortredByDescendingListOfFilms = listOfFilms.sortedByDescending { it.nameFilm }
        binding.ivSortDescending.setOnClickListener {
            adapter = MoviesListAdapter(sortredByDescendingListOfFilms)
            binding.rvMovies.adapter = adapter
        }
    }

    private fun sortByAscending() {
        val listOfFilms = presenter.listOfFilms
        val sortredByAscendingListOfFilms = listOfFilms.sortedBy { it.nameFilm }
        binding.ivSortAscending.setOnClickListener {
            adapter = MoviesListAdapter(sortredByAscendingListOfFilms)
            binding.rvMovies.adapter = adapter
        }
    }

}

