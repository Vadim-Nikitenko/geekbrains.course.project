package com.kiradev.nutritioncalc.mvp.model.entity.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.Tags

@Entity
data class RoomFood(
    @PrimaryKey var foodName: String,
    var servingQty: Int,
    var servingUnit: String,
    var servingWeightGrams: Float,
    var nfCalories: Float,
    var foodPicture: String?,
    @Embedded var tags: Tags
)