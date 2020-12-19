package com.kiradev.nutritioncalc.di.app.module

import android.widget.ImageView
import com.kiradev.nutritioncalc.mvp.model.image.IImageLoader
import com.kiradev.nutritioncalc.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun glideImageLoader(): IImageLoader<ImageView> = GlideImageLoader()

}