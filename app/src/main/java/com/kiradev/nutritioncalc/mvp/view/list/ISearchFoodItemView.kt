package com.kiradev.nutritioncalc.mvp.view.list

interface ISearchFoodItemView : IItemView {
    fun setData(foodName: String, calories: String, weight: String)
    fun loadImage(urL: String)
}