package com.kiradev.nutritioncalc.di

import com.kiradev.nutritioncalc.di.modules.*
import com.kiradev.nutritioncalc.mvp.presenter.FoodPresenter
import com.kiradev.nutritioncalc.mvp.presenter.MainPresenter
import com.kiradev.nutritioncalc.mvp.presenter.SearchFoodPresenter
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
        ImageModule::class,
        RepoModule::class,
        CacheModule::class
    ]
)
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(foodPresenter: FoodPresenter)
    fun inject(searchFoodPresenter: SearchFoodPresenter)
    fun inject(searchFoodRvAdapter: SearchFoodRvAdapter)
    fun inject(mainActivity: MainActivity)
    fun inject(foodFragment: FoodFragment)
}