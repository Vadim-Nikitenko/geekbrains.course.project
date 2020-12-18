package com.kiradev.nutritioncalc.mvp.model.entity.retrofit.query

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QueryFood (
    @Expose val query: String
) : Parcelable