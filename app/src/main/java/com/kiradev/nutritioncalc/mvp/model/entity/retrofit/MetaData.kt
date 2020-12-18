package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class MetaData(
    @Expose val isRawFood: Boolean
) : Parcelable