package com.kiradev.nutritioncalc.mvp.presenter.list

import com.kiradev.nutritioncalc.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}