package com.kiradev.nutritioncalc.mvp.model.repo

import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import io.reactivex.rxjava3.core.Single

interface IFoodRepo {
    fun getFoods(foodQuery: String): Single<List<FoodUnit>>
}