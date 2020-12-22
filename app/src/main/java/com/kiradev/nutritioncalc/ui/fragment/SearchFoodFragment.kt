package com.kiradev.nutritioncalc.ui.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiradev.nutritioncalc.databinding.FragmentSearchBinding
import com.kiradev.nutritioncalc.mvp.presenter.SearchFoodPresenter
import com.kiradev.nutritioncalc.mvp.view.SearchView
import com.kiradev.nutritioncalc.ui.App
import com.kiradev.nutritioncalc.ui.BackButtonListener
import com.kiradev.nutritioncalc.ui.adapter.SearchFoodRvAdapter
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.*

class SearchFoodFragment : MvpAppCompatFragment(), SearchView, BackButtonListener {

    private var binding: FragmentSearchBinding? = null
    var datePickerDialog: DatePickerDialog? = null

    companion object {
        fun newInstance() = SearchFoodFragment()
    }

    val presenter by moxyPresenter {
        SearchFoodPresenter { App.instance.releaseSearchFoodSubcomponent() }.apply {
            App.instance.searchFoodSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        datePickerDialog = with(Calendar.getInstance()) {
            DatePickerDialog(
                requireContext(),
                null,
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DAY_OF_MONTH)
            )
        }
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.initSearchFoodSubcomponent()
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        binding?.rvSearchFood?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSearchFood?.adapter =
            SearchFoodRvAdapter(presenter.searchFoodListPresenter).apply {
                App.instance.searchFoodSubcomponent?.inject(this)
            }
    }

    override fun updateFoodsList() = binding?.rvSearchFood?.adapter?.notifyDataSetChanged() ?: Unit

    override fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    override fun showDatePicker() {
        datePickerDialog?.show()
    }

    override fun hideDatePicker() {
        datePickerDialog?.hide()
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

        binding?.btnDate?.setOnClickListener {
            presenter.btnDateClicked()
        }

        datePickerDialog?.setOnDismissListener {
            presenter.datePickerDialogDismissed()
        }
    }

    override fun onDestroyView() {
        datePickerDialog = null
        binding = null
        super.onDestroyView()
    }

    override fun backPressed(): Boolean = presenter.backClick()

}