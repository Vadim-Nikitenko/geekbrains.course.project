package com.kiradev.nutritioncalc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kiradev.nutritioncalc.databinding.FragmentFoodBinding
import com.kiradev.nutritioncalc.mvp.model.constants.Constant.Companion.FOOD_KEY
import com.kiradev.nutritioncalc.mvp.model.entity.Food
import com.kiradev.nutritioncalc.mvp.model.entity.retrofit.FoodUnit
import com.kiradev.nutritioncalc.mvp.model.image.IImageLoader
import com.kiradev.nutritioncalc.mvp.presenter.FoodPresenter
import com.kiradev.nutritioncalc.mvp.view.FoodView
import com.kiradev.nutritioncalc.ui.App
import com.kiradev.nutritioncalc.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FoodFragment : MvpAppCompatFragment(), FoodView, BackButtonListener {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    private var binding: FragmentFoodBinding? = null

    companion object {
        fun newInstance(food: FoodUnit) = FoodFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FOOD_KEY, food)
            }
        }
    }

    val presenter by moxyPresenter {
        val food = arguments?.get(FOOD_KEY) as FoodUnit
        FoodPresenter(food).apply { App.instance.searchFoodSubcomponent?.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        App.instance.appComponent.inject(this)
        return binding?.root
    }

    override fun setFood(food: Food) {
        binding?.tvFoodName?.text = food.foodName
        binding?.tvCalories?.text = food.calories
        binding?.tvWeight?.text = food.servingWeightGrams
        binding?.ivImage?.let { imageLoader.loadInto(food.photoUrl, it) }
    }

    override fun backPressed(): Boolean {
        presenter.backClick()
        return true
    }
}