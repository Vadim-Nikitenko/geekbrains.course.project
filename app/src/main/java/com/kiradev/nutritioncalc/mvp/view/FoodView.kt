package com.kiradev.nutritioncalc.mvp.view

import com.kiradev.nutritioncalc.mvp.model.entity.Food
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FoodView : MvpView {
    fun setFood(food: Food)
}