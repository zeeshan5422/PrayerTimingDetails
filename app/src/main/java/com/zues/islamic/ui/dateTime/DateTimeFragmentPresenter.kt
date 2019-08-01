package com.zues.islamic.ui.dateTime

import com.zues.islamic.data.db.DB
import com.zues.islamic.data.network.MyApiInterface
import com.zues.islamic.models.Time
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* ---  Created by akhtarz on 6/28/2019. ---*/
class DateTimeFragmentPresenter constructor(val myDb: DB) : DateTimeFragmentContract.Presenter {

    lateinit var view: DateTimeFragmentContract.View

    override fun loadZone() {
        // TODO:: load zone from the datbase
//        val zones = listOf<String>()

        val zones: List<String> = myDb.myDbDao().getZone()

        view.onZoneLoaded(zones)
    }

    override fun loadCity(zone: String) {
        // TODO:: load cities from the datbase

        val zoneCities: List<String> = myDb.myDbDao().getCityByZone(zone)

        view.onZoneCitiesLoaded(zoneCities)
    }

    override fun getTime(selectedZone: String, selectedCity: String) {
        // Create service and get data from Api
        MyApiInterface.create()
            .getTime("$selectedZone/$selectedCity")
            .enqueue(object : Callback<Time> {
                override fun onFailure(call: Call<Time>, t: Throwable) {
                }
                override fun onResponse(call: Call<Time>, response: Response<Time>) {
                    if (response.code().equals(200)){
                        view.onGetTime(response.body()?.data as String)
                    }else if (response.code().equals(400)){
                        view.onGetError("Please specify a valid timezone")
                    }
                }
            })
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun attach(view: DateTimeFragmentContract.View) {
        this.view = view
    }
}