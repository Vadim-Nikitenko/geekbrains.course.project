package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AltMeasures(
    @Expose val servingWeight: Int,
    @Expose val measure: String,
    @Expose val seq: Int,
    @Expose val qty: Int
) : Parcelable