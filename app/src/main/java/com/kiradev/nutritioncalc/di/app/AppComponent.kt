package com.kiradev.nutritioncalc.di.app

import com.kiradev.nutritioncalc.di.app.module.*
import com.kiradev.nutritioncalc.di.search.SearchFoodSubcomponent
import com.kiradev.nutritioncalc.mvp.presenter.FoodPresenter
import com.kiradev.nutritioncalc.mvp.presenter.MainPresenter
import com.kiradev.nutritioncalc.ui.activity.MainActivity
import com.kiradev.nutritioncalc.ui.adapter.SearchFoodRvAdapter
import com.kiradev.nutritioncalc.ui.fragment.FoodFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        NavigationModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun searchFoodSubcomponent(): SearchFoodSubcomponent
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(foodFragment: FoodFragment)
}