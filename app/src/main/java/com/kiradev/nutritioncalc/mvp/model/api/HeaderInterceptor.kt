package com.kiradev.nutritioncalc.mvp.model.api

import com.kiradev.nutritioncalc.BuildConfig
import com.kiradev.nutritioncalc.mvp.model.constants.Constant.Companion.X_APP_ID_HEADER
import com.kiradev.nutritioncalc.mvp.model.constants.Constant.Companion.X_APP_KEY_HEADER
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        proceed(
            request()
                .newBuilder()
                .addHeader(X_APP_ID_HEADER, BuildConfig.X_APP_ID)
                .addHeader(X_APP_KEY_HEADER, BuildConfig.X_APP_KEY)
                .build()
        )
    }
}