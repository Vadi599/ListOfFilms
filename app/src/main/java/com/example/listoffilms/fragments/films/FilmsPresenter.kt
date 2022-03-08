package com.example.listoffilms.fragments.films

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class FilmsPresenter : MvpPresenter<FilmsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRecyclerView()
    }
}