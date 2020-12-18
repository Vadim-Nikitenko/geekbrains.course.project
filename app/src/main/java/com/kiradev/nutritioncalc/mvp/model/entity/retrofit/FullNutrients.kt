package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FullNutrients(
    @Expose val attrId: Int,
    @Expose val value: Double
) : Parcelable