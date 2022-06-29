package com.example.listoffilms.fragments.films

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilmsView : MvpView {

    fun initRecyclerView()

    fun movieFiltering()

    fun sortByDescending()

    fun sortByAscending()

    fun unSelectAllFilterButtons()

}