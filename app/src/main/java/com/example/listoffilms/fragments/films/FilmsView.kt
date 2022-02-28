package com.example.listoffilms.fragments.films

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.listoffilms.model.Movie

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilmsView : MvpView {

    fun showMovies(movies: List<Movie>)

}