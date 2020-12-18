package com.kiradev.nutritioncalc.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.kiradev.nutritioncalc.mvp.model.entity.room.RoomFood
import com.kiradev.nutritioncalc.mvp.model.entity.room.dao.FoodDao

@androidx.room.Database(entities = [RoomFood::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val foodDao: FoodDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }

}