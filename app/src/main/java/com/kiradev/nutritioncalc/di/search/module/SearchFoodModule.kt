package com.kiradev.nutritioncalc.di.search.module

import com.kiradev.nutritioncalc.di.search.SearchFoodScope
import com.kiradev.nutritioncalc.mvp.model.api.IDataSource
import com.kiradev.nutritioncalc.mvp.model.cache.IFoodCache
import com.kiradev.nutritioncalc.mvp.model.entity.room.db.Database
import com.kiradev.nutritioncalc.mvp.model.network.INetworkStatus
import com.kiradev.nutritioncalc.mvp.model.repo.IFoodRepo
import com.kiradev.nutritioncalc.mvp.model.repo.RetrofitFoodRepo
import com.kiradev.nutritioncalc.ui.cache.RoomFoodCache
import dagger.Module
import dagger.Provides

@Module
class SearchFoodModule {

    @SearchFoodScope
    @Provides
    fun foodsRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IFoodCache) : IFoodRepo = RetrofitFoodRepo(api, networkStatus, cache)

    @SearchFoodScope
    @Provides
    fun foodsCache(database: Database): IFoodCache = RoomFoodCache(database)
}