package com.kiradev.nutritioncalc.di.modules

import androidx.room.Room
import com.kiradev.nutritioncalc.mvp.model.cache.IFoodCache
import com.kiradev.nutritioncalc.mvp.model.entity.room.db.Database
import com.kiradev.nutritioncalc.ui.App
import com.kiradev.nutritioncalc.ui.cache.RoomFoodCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun foodsCache(database: Database): IFoodCache = RoomFoodCache(database)


}