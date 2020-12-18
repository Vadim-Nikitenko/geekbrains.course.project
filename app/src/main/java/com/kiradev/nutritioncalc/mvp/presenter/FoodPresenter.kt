package com.kiradev.nutritioncalc.mvp.presenter

import com.kiradev.nutritioncalc.mvp.model.entity.Food
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.mvp.view.FoodView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FoodPresenter(private val food: FoodUnit) : MvpPresenter<FoodView>() {

    @Inject
    lateinit var scheduler: Scheduler

    @Inject
    lateinit var router: Router
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData(food)
    }

    fun loadData(food: FoodUnit) {
        val foodModel = Food(
            food.foodName,
            food.servingQty.toString(),
            food.servingUnit,
            food.servingWeightGrams.toString() + " g",
            food.nfCalories.toString() + " kcal",
            food.photo.thumb.toString()
        )
        viewState.setFood(foodModel)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}