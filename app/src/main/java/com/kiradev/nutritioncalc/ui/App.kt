package com.kiradev.nutritioncalc.ui

import android.app.Application
import android.view.View
import com.kiradev.nutritioncalc.di.AppComponent
import com.kiradev.nutritioncalc.di.DaggerAppComponent
import com.kiradev.nutritioncalc.di.modules.AppModule
import leakcanary.LeakCanary

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}