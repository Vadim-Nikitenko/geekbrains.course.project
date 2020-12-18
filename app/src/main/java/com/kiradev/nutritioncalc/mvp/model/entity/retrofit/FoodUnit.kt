package com.kiradev.nutritioncalc.mvp.model.entity.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodUnit(
    @Expose val foodName: String,
//    @Expose val brandName: String,
    @Expose val servingQty: Int,
    @Expose val servingUnit: String,
    @Expose val servingWeightGrams: Float,
    @Expose val nfCalories: Float,
//    @Expose val nfTotalFat: Double,
//    @Expose val nfSaturatedFat: Double,
//    @Expose val nfCholesterol: Int,
//    @Expose val nfSodium: Int,
//    @Expose val nfTotalCarbohydrate: Double,
//    @Expose val nfDietaryFiber: Int,
//    @Expose val nfSugars: Double,
//    @Expose val nfProtein: Double,
//    @Expose val nfPotassium: Int,
//    @Expose val nfP: Int,
//    @Expose val fullNutrients: List<FullNutrients>,
//    @Expose val nixBrandName: String,
//    @Expose val nixBrandId: String,
//    @Expose val nixItemName: String,
//    @Expose val nixItemId: String,
//    @Expose val upc: String,
//    @Expose val consumedAt: String,
//    @Expose val metadata: MetaData,
//    @Expose val source: Int,
//    @Expose val ndbNo: Int,
    @Expose val tags: Tags,
//    @Expose val altMeasures: List<AltMeasures>,
//    @Expose val lat: String,
//    @Expose val lng: String,
//    @Expose val mealType: Int,
    @Expose val photo: Photo
//    @Expose val subRecipe: String
) : Parcelable