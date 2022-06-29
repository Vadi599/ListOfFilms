package com.example.listoffilms.fragments.films

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.listoffilms.R
import com.example.listoffilms.adapter.MoviesListAdapter
import com.example.listoffilms.databinding.FragmentFilmsBinding
import com.example.listoffilms.model.Movie

class FilmsFragment : Fragment(), FilmsView {

    private lateinit var binding: FragmentFilmsBinding

    lateinit var presenter: FilmsPresenter

    private var movies: ArrayList<Movie> = arrayListOf()

    private var matchedMovies: ArrayList<Movie> = arrayListOf()

    private var selectedFilters: ArrayList<String> = arrayListOf()

    private var currentSearchText = ""

    private var white = 0

    private var darkGray: Int = 0

    private var red: Int = 0

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
        movieFiltering()
        initColors()
        lookSelected(binding.btnAllFilter)
        selectedFilters.add("all")
        return view
    }

    private fun performSearch() {
        binding.filmSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun initColors() {
        white = ContextCompat.getColor(requireContext(), R.color.white)
        red = ContextCompat.getColor(requireContext(), R.color.red)
        darkGray = ContextCompat.getColor(requireContext(), R.color.darker_gray)
    }

    override fun unSelectAllFilterButtons() {
        binding.run {
            lookUnSelected(btnComedyFilter)
            lookUnSelected(btnAllFilter)
            lookUnSelected(btnDramaFilter)
            lookUnSelected(btnThrillerFilter)
            lookUnSelected(btnDocumentaryFilter)
            lookUnSelected(btn2014Filter)
            lookUnSelected(btn2022Filter)
            lookUnSelected(btn2021Filter)
            lookUnSelected(btn2018Filter)
            lookUnSelected(btn2017Filter)
            lookUnSelected(btn2015Filter)
            lookUnSelected(btn2020Filter)
        }

    }

    private fun lookSelected(parsedButton: Button) {
        parsedButton.setTextColor(white)
        parsedButton.setBackgroundColor(red)
    }

    private fun lookUnSelected(parsedButton: Button) {
        parsedButton.setTextColor(white)
        parsedButton.setBackgroundColor(darkGray)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initRecyclerView() {
        movies = arrayListOf(
            Movie(
                "Donbass",
                "https://static01.nyt.com/images/2022/04/07/arts/07dombass-review/" +
                        "07dombass-review-mediumThreeByTwo440.jpg",
                "2022", "thriller"
            ),
            Movie(
                "The Girl and the Spider",
                "https://static01.nyt.com/images/2022/04/06/arts/girl1/girl1-mediumThreeByTwo440.jpg",
                "2021", "comedy"
            ),
            Movie(
                "Cow",
                "https://static01.nyt.com/images/2022/04/06/arts/cow1/" +
                        "cow1-mediumThreeByTwo440.jpg", "2020", "documentary"
            ),
            Movie(
                "a-ha: The Movie", "https://static01.nyt.com/" +
                        "images/2022/04/06/arts/aha1/aha1-mediumThreeByTwo440.jpg", "2018",
                "documentary"
            ),
            Movie(
                "¡Viva Maestro!", "https://static01.nyt.com/" +
                        "images/2022/04/07/arts/07viva-maestro-art/" +
                        "merlin_204843759_8ac0da1e-2154-46fa-8a56-4c985db7f242-mediumThreeByTwo440.jpg",
                "2022", "drama"
            ),
            Movie(
                "Sonic the Hedgehog 2",
                "https://static01.nyt.com/images/2022/04/07/multimedia/" +
                        "07sonic-review/07sonic-review-mediumThreeByTwo440.jpg", "2015",
                "comedy"
            ),
            Movie(
                "Aline",
                "https://static01.nyt.com/images/2022/04/07/multimedia/" +
                        "07aline-review/07aline-review-mediumThreeByTwo440.jpg",
                "2014",
                "comedy"
            ),
            Movie(
                "Metal Lords", "https://static01.nyt.com/images/2022/04/07/" +
                        "arts/07metal-lords-art/" +
                        "merlin_204842160_43efd1f2-b74f-467f-a87b-0df43247fd0d-mediumThreeByTwo440.jpg",
                "2020", "comedy"
            ),
            Movie(
                "All the Old Knives", "https://static01.nyt.com/images/2022/04/" +
                        "08/arts/all-the-old2/all-the-old2-mediumThreeByTwo440.jpg", "2017",
                "thriller"
            ),
            Movie(
                "Return to Space",
                "https://static01.nyt.com/images/2022/04/06/arts/return1/" +
                        "return1-mediumThreeByTwo440.jpg", "2018", "documentary"
            )
        )
        adapter = MoviesListAdapter(movies).also {
            binding.rvMovies.adapter = it
            binding.rvMovies.adapter?.notifyDataSetChanged()
        }
    }

    override fun sortByDescending() {
        val sortedByDescendingListOfFilms = movies.sortedByDescending { it.nameFilm }
        binding.ivSortDescending.setOnClickListener {
            adapter = MoviesListAdapter(sortedByDescendingListOfFilms)
            binding.rvMovies.adapter = adapter
            unSelectAllFilterButtons()
            lookSelected(binding.btnAllFilter)
        }
    }

    override fun sortByAscending() {
        val sortedByAscendingListOfFilms = movies.sortedBy { it.nameFilm }
        binding.ivSortAscending.setOnClickListener {
            adapter = MoviesListAdapter(sortedByAscendingListOfFilms)
            binding.rvMovies.adapter = adapter
            unSelectAllFilterButtons()
            lookSelected(binding.btnAllFilter)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView() {
        binding.rvMovies.adapter.apply {
            adapter.moviesList = matchedMovies
            adapter.notifyDataSetChanged()
        }
    }

    private fun search(searchText: String) {
        currentSearchText = searchText
        matchedMovies = arrayListOf()
        searchText.let {
            movies.forEach { movie ->
                if (movie.nameFilm.contains(searchText, true)
                ) {
                    if (selectedFilters.contains("all")) {
                        matchedMovies.add(movie)
                    }
                }
            }
            unSelectAllFilterButtons()
            lookSelected(binding.btnAllFilter)
            updateRecyclerView()
        }
    }

    private fun filteringByGenre(status: String) {
        if (!selectedFilters.contains(status)) {
            selectedFilters.add(status)
        }
        matchedMovies = arrayListOf()
        status.let {
            movies.forEach { movie ->
                selectedFilters.forEach { selectedFilter ->
                    if (movie.genreFilm.contains(selectedFilter, true)) {
                        matchedMovies.add(movie)
                    }
                }
            }
        }
        updateRecyclerView()
    }

    private fun filteringByYear(status: String) {
        if (!selectedFilters.contains(status)) {
            selectedFilters.add(status)
        }
        matchedMovies = arrayListOf()
        status.let {
            movies.forEach { movie ->
                selectedFilters.forEach { selectedFilter ->
                    if (movie.yearFilm.contains(selectedFilter, true)) {
                        matchedMovies.add(movie)
                    }
                }
            }
        }
        updateRecyclerView()
    }

    override fun movieFiltering() {
        binding.run {
            btnAllFilter.setOnClickListener {
                selectedFilters.clear()
                selectedFilters.add("all")
                initRecyclerView()
                unSelectAllFilterButtons()
                lookSelected(btnAllFilter)
            }

            btnComedyFilter.setOnClickListener {
                filteringByGenre("comedy")
                lookUnSelected(btnAllFilter)
                lookSelected(btnComedyFilter)
            }

            btnDocumentaryFilter.setOnClickListener {
                filteringByGenre("documentary")
                lookUnSelected(btnAllFilter)
                lookSelected(btnDocumentaryFilter)
            }

            btnThrillerFilter.setOnClickListener {
                filteringByGenre("thriller")
                lookUnSelected(btnAllFilter)
                lookSelected(btnThrillerFilter)
            }

            btnDramaFilter.setOnClickListener {
                filteringByGenre("drama")
                lookUnSelected(btnAllFilter)
                lookSelected(btnDramaFilter)
            }
            btn2015Filter.setOnClickListener {
                filteringByYear("2015")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2015Filter)
            }

            btn2017Filter.setOnClickListener {
                filteringByYear("2017")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2017Filter)
            }

            btn2018Filter.setOnClickListener {
                filteringByYear("2018")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2018Filter)
            }

            btn2020Filter.setOnClickListener {
                filteringByYear("2020")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2020Filter)
            }

            btn2021Filter.setOnClickListener {
                filteringByYear("2021")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2021Filter)
            }

            btn2022Filter.setOnClickListener {
                filteringByYear("2022")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2022Filter)
            }

            btn2014Filter.setOnClickListener {
                filteringByYear("2014")
                lookUnSelected(btnAllFilter)
                lookSelected(btn2014Filter)
            }
        }
    }
}

