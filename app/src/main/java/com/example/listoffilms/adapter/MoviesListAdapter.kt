package com.example.listoffilms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listoffilms.databinding.ListItemMoviesBinding
import com.example.listoffilms.model.Movie

class MoviesListAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ListItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    fun setResultsList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var nameFilm = binding.tvTitleFilm
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameFilm.text = movies[position].nameFilm
    }

}