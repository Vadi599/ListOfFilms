package com.example.listoffilms.fragments.films

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.listoffilms.adapter.MoviesListAdapter
import com.example.listoffilms.databinding.FragmentFilmsBinding
import com.example.listoffilms.model.Movie

class FilmsFragment : Fragment(), FilmsView {

    private lateinit var binding: FragmentFilmsBinding

    lateinit var presenter: FilmsPresenter

    private var movies: ArrayList<Movie> = arrayListOf()

    private var matchedMovies: ArrayList<Movie> = arrayListOf()

    var adapter: MoviesListAdapter = MoviesListAdapter(movies)

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
        initRecyclerView()
        performSearch()
        sortByDescending()
        sortByAscending()
        return view
    }

    private fun performSearch() {
        binding.filmSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initRecyclerView() {
        movies = arrayListOf(
            Movie("Servants"),
            Movie("Three Months"),
            Movie("Creation Stories"),
            Movie("The Burning Sea"),
            Movie("Hellbender"),
            Movie("Careless Crime"),
            Movie("Family Squares"),
            Movie("Butter"),
            Movie("Studio 666"),
            Movie("Friends and Strangers")
        )
        adapter = MoviesListAdapter(movies).also {
            binding.rvMovies.adapter = it
            binding.rvMovies.adapter?.notifyDataSetChanged()
        }
    }

    private fun sortByDescending() {
        val sortedByDescendingListOfFilms = movies.sortedByDescending { it.nameFilm }
        binding.ivSortDescending.setOnClickListener {
            adapter = MoviesListAdapter(sortedByDescendingListOfFilms)
            binding.rvMovies.adapter = adapter
        }
    }

    private fun sortByAscending() {
        val sortedByAscendingListOfFilms = movies.sortedBy { it.nameFilm }
        binding.ivSortAscending.setOnClickListener {
            adapter = MoviesListAdapter(sortedByAscendingListOfFilms)
            binding.rvMovies.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateRecyclerView() {
        binding.rvMovies.adapter.apply {
            adapter.moviesList = matchedMovies
            adapter.notifyDataSetChanged()
        }
    }

    private fun search(text: String?) {
        matchedMovies = arrayListOf()

        text?.let {
            movies.forEach { movie ->
                if (movie.nameFilm.contains(text, true)
                ) {
                    matchedMovies.add(movie)
                }
            }
            updateRecyclerView()
        }
    }
}

