package com.zues.islamic.ui.dateTime

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.R
import com.zues.islamic.di.component.DaggerFragmentComponent
import com.zues.islamic.ui.main.MainActivity
import kotlinx.android.synthetic.main.date_time_fragment.*
import javax.inject.Inject

/* ---  Created by akhtarz on 6/28/2019. ---*/
class DateTimeFragment : BaseFragment(), DateTimeFragmentContract.View {

    @Inject
    lateinit var presenter: DateTimeFragmentContract.Presenter

    lateinit var selectedZone: String
    lateinit var selectedCity: String

    override val layoutId: Int
        get() = R.layout.date_time_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        presenter.attach(this)
        presenter.loadZone()
    }

    override fun onZoneLoaded(zones: List<String>) {

        val arrayAdapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, zones)
        sp_zone.adapter = arrayAdapter

        sp_zone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedZone = zones.get(position)
                presenter.loadCity(selectedZone)
                datetime_header.text = ""
            }
        }
    }

    override fun onZoneCitiesLoaded(cities: List<String>) {
        val arrayAdapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, cities)
        sp_zone_cities.adapter = arrayAdapter

        sp_zone_cities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCity = cities.get(position)
                presenter.getTime(selectedZone, selectedCity)
            }
        }
    }

    override fun onGetTime(time: String) {
        val result: String = "ZONE : ${selectedZone} \n" +
                "CITY : $selectedCity \n" +
                "\n" +
                "Time : $time"

        datetime_header.text = result
    }

    override fun onGetError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        datetime_header.text = ""
    }

    private fun injectDependency() {
        val component = DaggerFragmentComponent.builder()
            .activityModule((activity as MainActivity).component)
            .build()
        component.inject(this)
    }

    override fun getTitle(): String {
        return "Time API"
    }
}