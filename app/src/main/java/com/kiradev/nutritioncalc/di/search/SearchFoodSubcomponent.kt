package com.kiradev.nutritioncalc.di.search

import com.kiradev.nutritioncalc.di.search.module.SearchFoodModule
import com.kiradev.nutritioncalc.mvp.presenter.FoodPresenter
import com.kiradev.nutritioncalc.mvp.presenter.SearchFoodPresenter
import com.kiradev.nutritioncalc.ui.adapter.SearchFoodRvAdapter
import dagger.Subcomponent

@SearchFoodScope
@Subcomponent(
    modules = [SearchFoodModule::class]
)
interface SearchFoodSubcomponent {

    fun inject(searchFoodPresenter: SearchFoodPresenter)
    fun inject(rvAdapter: SearchFoodRvAdapter)
    fun inject(foodPresenter: FoodPresenter)

}