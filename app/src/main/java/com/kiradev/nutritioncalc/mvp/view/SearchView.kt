package com.kiradev.nutritioncalc.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SearchView: MvpView {
    fun init()
    fun updateFoodsList()
    fun hideKeyboard()
    fun showDatePicker()
    fun hideDatePicker()
}