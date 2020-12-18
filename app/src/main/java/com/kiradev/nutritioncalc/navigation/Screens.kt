package com.kiradev.nutritioncalc.navigation

import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.ui.fragment.FoodFragment
import com.kiradev.nutritioncalc.ui.fragment.SearchFoodFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class SearchScreen() : SupportAppScreen() {
        override fun getFragment() = SearchFoodFragment.newInstance()
    }

    class FoodScreen(val food: FoodUnit) : SupportAppScreen() {
        override fun getFragment() = FoodFragment.newInstance(food)
    }

}