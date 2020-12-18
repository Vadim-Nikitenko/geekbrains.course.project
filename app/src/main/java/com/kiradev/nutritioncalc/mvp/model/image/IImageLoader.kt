package com.kiradev.nutritioncalc.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}