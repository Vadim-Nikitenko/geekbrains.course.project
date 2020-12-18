package com.kiradev.nutritioncalc.ui.cache

import com.kiradev.nutritioncalc.mvp.model.cache.IFoodCache
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.Photo
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.Tags
import com.kiradev.nutritioncalc.mvp.model.entity.room.RoomFood
import com.kiradev.nutritioncalc.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomFoodCache(val db: Database): IFoodCache {
    override fun putFoods(foods: List<FoodUnit>?): Completable = Completable.fromCallable {
        val roomFoods = foods?.map { food ->
            RoomFood(
                food.foodName ?: "",
                food.servingQty,
                food.servingUnit,
                food.servingWeightGrams,
                food.nfCalories,
                food.photo.thumb,
                Tags(food.tags.foodGroup)
            )
        }
        roomFoods?.let { db.foodDao.insert(it) }
        foods
    }

    override fun getFoods(): Single<List<FoodUnit>> =
        Single.fromCallable {
            db.foodDao.getAll().map { food ->
                FoodUnit(
                    food.foodName,
                    food.servingQty,
                    food.servingUnit,
                    food.servingWeightGrams,
                    food.nfCalories,
                    Tags(food.tags.foodGroup),
                    Photo(
                        food.foodPicture
                    )
                )
            }
        }
}