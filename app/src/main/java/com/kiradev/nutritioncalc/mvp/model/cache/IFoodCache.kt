package com.kiradev.nutritioncalc.mvp.model.cache

import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IFoodCache {
    fun putFoods(foods: List<FoodUnit>?): Completable
    fun getFoods(): Single<List<FoodUnit>>
}