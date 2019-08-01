package com.zues.islamic.ui.prayerTiming

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.R
import com.zues.islamic.di.component.DaggerFragmentComponent
import com.zues.islamic.models.Data
import com.zues.islamic.ui.main.MainActivity
import com.zues.searchable_spinner.SearchableSpinner
import kotlinx.android.synthetic.main.fragment_prayer_timing.*
import javax.inject.Inject

/* ---  Created by akhtarz on 7/4/2019. ---*/
class PrayerTimingFragment : BaseFragment(), PrayerTimingFragContract.View {

    @Inject
    lateinit var presenter: PrayerTimingFragContract.Presenter

    override val layoutId: Int
        get() = R.layout.fragment_prayer_timing

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)

        injectDependencies()

        presenter.attach(this)
        presenter.subscribe()
    }

    override fun onDataLoaded(data: Data) {
        datetime_header.text = data.timings.toString()

        country_spinner.setItems(getListOfItems())
        country_spinner.setOnItemSelectListener(object : SearchableSpinner.SearchableItemListener {

            override fun onItemSelected(view: View?, position: Int) {
                showToast("item is selected $position")
            }

            override fun onSelectionClear() {
                showToast("Item is cleared")
            }
        })

        city_spinner.setItems(getListOfItems())
        city_spinner.setOnItemSelectListener(object : SearchableSpinner.SearchableItemListener {

            override fun onItemSelected(view: View?, position: Int) {
                showToast("item is selected $position")
            }

            override fun onSelectionClear() {
                showToast("Item is cleared")
            }
        })
    }

    override fun onCalenderDataLoaded(data: List<Data>) {
//        for (i in data.indices){
//            showToast(data.toString())
//        }
        Log.v("Prayer timing Fragment ", data.toString())
//        showToast(data.toString())
        // create adapter and show details in recycler view.

    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun getListOfItems(): List<Any> {
        return listOf(
            "A 45",
            "B 45",
            "C f",
            "D v",
            "E f",
            "Fff",
            "Ffa",
            "G",
            "H",
            "Z",
            "Y",
            "X",
            "W",
            "L"
        )
    }

    private fun injectDependencies() {
        val component = DaggerFragmentComponent.builder()
            .activityModule((activity as MainActivity).component)
            .build()
        component.inject(this)
    }
}