package com.kiradev.nutritioncalc.di.modules

import com.kiradev.nutritioncalc.mvp.model.api.IDataSource
import com.kiradev.nutritioncalc.mvp.model.cache.IFoodCache
import com.kiradev.nutritioncalc.mvp.model.network.INetworkStatus
import com.kiradev.nutritioncalc.mvp.model.repo.IFoodRepo
import com.kiradev.nutritioncalc.mvp.model.repo.RetrofitFoodRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun foodsRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IFoodCache) : IFoodRepo = RetrofitFoodRepo(api, networkStatus, cache)
}