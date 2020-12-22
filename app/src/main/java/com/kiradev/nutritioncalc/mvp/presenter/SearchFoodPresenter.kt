package com.kiradev.nutritioncalc.mvp.presenter

import com.kiradev.nutritioncalc.mvp.model.constants.Constant.Companion.INIT_FOODS
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.mvp.model.repo.IFoodRepo
import com.kiradev.nutritioncalc.mvp.presenter.list.ISearchFoodListPresenter
import com.kiradev.nutritioncalc.mvp.view.SearchView
import com.kiradev.nutritioncalc.mvp.view.list.ISearchFoodItemView
import com.kiradev.nutritioncalc.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SearchFoodPresenter(val onDestroyF: () -> Unit) : MvpPresenter<SearchView>() {

    @Inject lateinit var foodsRepo: IFoodRepo
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var router: Router

    val searchFoodListPresenter = SearchFoodListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        searchFoodListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.FoodScreen(searchFoodListPresenter.foods[view.pos]))
        }

    }

    private fun loadData() {
        foodsRepo.getFoods(INIT_FOODS)
            .observeOn(uiScheduler)
            .subscribe({
                searchFoodListPresenter.foods.clear()
                searchFoodListPresenter.foods.addAll(it)
                viewState.updateFoodsList()
            }, {
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        onDestroyF()
    }

    fun updateFoods(text: CharSequence) {
        foodsRepo.getFoods(text.toString())
            .observeOn(uiScheduler)
            .subscribe({
                searchFoodListPresenter.foods.clear()
                searchFoodListPresenter.foods.addAll(it)
                viewState.updateFoodsList()
            }, {
                it.printStackTrace()
            }).addTo(compositeDisposable)
        viewState.updateFoodsList()
        viewState.hideKeyboard()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    fun btnDateClicked() {
        viewState.showDatePicker()
    }

    fun datePickerDialogDismissed() {
        viewState.hideDatePicker()
    }


    class SearchFoodListPresenter : ISearchFoodListPresenter {
        override var itemClickListener: ((ISearchFoodItemView) -> Unit)? = null

        val foods = mutableListOf<FoodUnit>()

        override fun bindView(view: ISearchFoodItemView) {
            val food = foods[view.pos]
            view.setData(
                    food.foodName,
                    food.nfCalories.toString() + " kcal",
                    food.servingWeightGrams.toString() + " g"
            )
            food.photo.thumb?.let { view.loadImage(it) }
        }

        override fun getCount() = foods.size
    }


}