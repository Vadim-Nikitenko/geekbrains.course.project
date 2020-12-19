package com.kiradev.nutritioncalc.ui

import android.app.Application
import com.kiradev.nutritioncalc.di.app.AppComponent
import com.kiradev.nutritioncalc.di.app.DaggerAppComponent
import com.kiradev.nutritioncalc.di.app.module.AppModule
import com.kiradev.nutritioncalc.di.search.SearchFoodSubcomponent

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var searchFoodSubcomponent: SearchFoodSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initSearchFoodSubcomponent() = appComponent.searchFoodSubcomponent().also {
        searchFoodSubcomponent = it
    }

    fun releaseSearchFoodSubcomponent() {
        searchFoodSubcomponent = null
    }
}