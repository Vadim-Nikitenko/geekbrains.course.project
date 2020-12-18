package com.kiradev.nutritioncalc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kiradev.nutritioncalc.databinding.ItemFoodBinding
import com.kiradev.nutritioncalc.mvp.model.image.IImageLoader
import com.kiradev.nutritioncalc.mvp.presenter.SearchFoodPresenter
import com.kiradev.nutritioncalc.mvp.view.list.ISearchFoodItemView
import javax.inject.Inject

class SearchFoodRvAdapter (val presenter: SearchFoodPresenter.SearchFoodListPresenter) :
    RecyclerView.Adapter<SearchFoodRvAdapter.ViewHolderSearch>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>
    override fun onBindViewHolder(holder: ViewHolderSearch, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearch {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(layoutInflater, parent, false)
        return ViewHolderSearch(binding).apply {
            binding.container.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    inner class ViewHolderSearch(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root), ISearchFoodItemView {
        override var pos = -1
        override fun setData(foodName: String, calories: String, weight: String) {
            binding.tvFoodName.text = foodName
            binding.tvCalories.text = calories
            binding.tvWeight.text = weight
        }

        override fun loadImage(urL: String) {
            imageLoader.loadInto(urL, binding.ivImage)
        }
    }


}