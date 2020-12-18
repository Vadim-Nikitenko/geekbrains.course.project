package com.kiradev.nutritioncalc.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kiradev.nutritioncalc.mvp.model.entity.room.RoomFood

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: RoomFood)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg foods: RoomFood)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(foods: List<RoomFood>)

    @Update
    fun update(food: RoomFood)

    @Update
    fun update(vararg foods: RoomFood)

    @Update
    fun update(foods: List<RoomFood>)

    @Delete
    fun delete(food: RoomFood)

    @Delete
    fun delete(vararg foods: RoomFood)

    @Delete
    fun delete(foods: List<RoomFood>)

    @Query("SELECT * FROM RoomFood")
    fun getAll(): List<RoomFood>

    @Query("SELECT * FROM RoomFood WHERE foodName = :foodName LIMIT 1")
    fun findByFoodName(foodName: String): RoomFood?

}