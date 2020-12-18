package com.kiradev.nutritioncalc.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiradev.nutritioncalc.mvp.model.api.HeaderInterceptor
import com.kiradev.nutritioncalc.mvp.model.api.IDataSource
import com.kiradev.nutritioncalc.di.constants.Constant.Companion.BASE_URL
import com.kiradev.nutritioncalc.di.constants.Constant.Companion.CACHE_DIRECTORY_NAME
import com.kiradev.nutritioncalc.di.constants.Constant.Companion.CACHE_SIZE
import com.kiradev.nutritioncalc.mvp.model.network.INetworkStatus
import com.kiradev.nutritioncalc.ui.App
import com.kiradev.nutritioncalc.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl() = BASE_URL

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson, client: OkHttpClient): IDataSource =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)

    @Singleton
    @Provides
    fun client(app: App) = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addNetworkInterceptor(HeaderInterceptor())
        .cache(app.getExternalFilesDir(CACHE_DIRECTORY_NAME)?.let { Cache(it, CACHE_SIZE) })
        .build()
}