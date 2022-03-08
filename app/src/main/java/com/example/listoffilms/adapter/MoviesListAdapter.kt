package com.example.listoffilms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listoffilms.databinding.ListItemMoviesBinding
import com.example.listoffilms.model.Movie


class MoviesListAdapter(var moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            moviesList[position]
        )
    }

    inner class ViewHolder(private var binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitleFilm.text = movie.nameFilm.trim()
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}
