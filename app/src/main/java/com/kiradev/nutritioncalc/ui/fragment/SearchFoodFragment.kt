package com.kiradev.nutritioncalc.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiradev.nutritioncalc.databinding.FragmentSearchBinding
import com.kiradev.nutritioncalc.di.search.SearchFoodSubcomponent
import com.kiradev.nutritioncalc.mvp.presenter.SearchFoodPresenter
import com.kiradev.nutritioncalc.mvp.view.SearchView
import com.kiradev.nutritioncalc.ui.App
import com.kiradev.nutritioncalc.ui.BackButtonListener
import com.kiradev.nutritioncalc.ui.adapter.SearchFoodRvAdapter
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SearchFoodFragment : MvpAppCompatFragment(), SearchView, BackButtonListener {

    private var binding: FragmentSearchBinding? = null

    companion object {
        fun newInstance() = SearchFoodFragment()
    }


    val presenter by moxyPresenter {
        App.instance.initSearchFoodSubcomponent()
        SearchFoodPresenter().apply {
            App.instance.searchFoodSubcomponent?.inject(this)
        }
    }

    private val adapter by lazy {
        SearchFoodRvAdapter(presenter.searchFoodListPresenter).apply {
            App.instance.searchFoodSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun init() {
        binding?.rvSearchFood?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSearchFood?.adapter = adapter
    }

    override fun updateFoodsList() = adapter.notifyDataSetChanged()

    override fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            false
        )

        binding?.tietAddMeal?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT && requireActivity().currentFocus != null) {
                presenter.updateFoods(v.text)
            }
            return@setOnEditorActionListener true;
        }
    }


    override fun backPressed(): Boolean = presenter.backClick()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        App.instance.releaseSearchFoodSubcomponent()
    }

}