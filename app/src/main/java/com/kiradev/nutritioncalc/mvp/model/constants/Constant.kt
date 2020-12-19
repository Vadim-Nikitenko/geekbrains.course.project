package com.kiradev.nutritioncalc.mvp.model.constants

class Constant {
    companion object {
        const val X_APP_ID_HEADER: String = "x-app-id"
        const val X_APP_KEY_HEADER: String = "x-app-key"
        const val BASE_URL: String = "https://trackapi.nutritionix.com/v2/"
        const val CACHE_DIRECTORY_NAME = "cache"
        const val CACHE_SIZE = 10L * 1024L * 1024L
        const val INIT_FOODS: String = "for breakfast i ate 2 eggs, bacon, and french toast"
        const val FOOD_KEY: String = "food_key"
    }
}