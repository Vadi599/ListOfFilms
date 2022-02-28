package com.example.listoffilms.fragments.films

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.listoffilms.model.Movie

@InjectViewState
class FilmsPresenter : MvpPresenter<FilmsView>() {

    val listOfFilms = listOf(
        Movie("Servants"),
        Movie("Friends and Strangers"),
        Movie("Three Months"),
        Movie("Creation Stories"),
        Movie("The Burning Sea"),
        Movie("Hellbender"),
        Movie("Careless Crime"),
        Movie("Family Squares"),
        Movie("Big Gold Brick"),
        Movie("Butter"),
        Movie("Studio 666")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showMovies(listOfFilms)
    }
}