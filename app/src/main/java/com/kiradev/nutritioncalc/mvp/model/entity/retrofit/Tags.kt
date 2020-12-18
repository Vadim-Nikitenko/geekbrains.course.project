package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tags(
//    @Expose val item: String,
//    @Expose val measure: String,
//    @Expose val quantity: Int,
    @Expose val foodGroup: Int
//    @Expose val tagId: Int
) : Parcelable