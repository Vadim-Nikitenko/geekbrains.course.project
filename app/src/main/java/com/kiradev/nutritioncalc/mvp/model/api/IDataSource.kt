package com.kiradev.nutritioncalc.mvp.model.api

import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.Foods
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.query.QueryFood
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface IDataSource {

    @POST("natural/nutrients")
    fun getFoods(@Body query: QueryFood): Single<Foods>

}