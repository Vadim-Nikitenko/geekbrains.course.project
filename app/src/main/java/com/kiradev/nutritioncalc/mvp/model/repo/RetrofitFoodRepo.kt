package com.kiradev.nutritioncalc.mvp.model.repo

import com.kiradev.nutritioncalc.mvp.model.api.IDataSource
import com.kiradev.nutritioncalc.mvp.model.cache.IFoodCache
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.query.QueryFood
import com.kiradev.nutritioncalc.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitFoodRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cache: IFoodCache) : IFoodRepo {

    override fun getFoods(foodQuery: String): Single<List<FoodUnit>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getFoods(QueryFood(foodQuery)).flatMap { foodsList ->
                    cache.putFoods(foodsList.foods).andThen(Single.just(foodsList.foods))
                }
            } else {
                cache.getFoods()
            }
        }.subscribeOn(Schedulers.io())
}