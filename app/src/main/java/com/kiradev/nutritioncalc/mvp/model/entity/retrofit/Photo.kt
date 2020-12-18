package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @Expose val thumb: String?
//    @Expose val highres: String,
//    @Expose val isUserUploaded: Boolean
) : Parcelable