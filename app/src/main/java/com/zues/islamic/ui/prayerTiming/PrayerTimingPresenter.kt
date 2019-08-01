package com.zues.islamic.ui.prayerTiming

import android.util.Log
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.network.MyApiInterface
import com.zues.islamic.models.CalenderByCity
import com.zues.islamic.models.CalenderData
import com.zues.islamic.models.Data
import com.zues.islamic.models.TimeByLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PrayerTimingPresenter(val myDb: DB) : PrayerTimingFragContract.Presenter {


    lateinit var view: PrayerTimingFragContract.View

    override fun subscribe() {
        getTimingsFromServer()
        getCalenderTimingsFromServer()
    }

    override fun unSubscribe() {
    }

    private fun getTimingsFromServer() {
        MyApiInterface.create()
            .getPrayerTimingByAddress("Karachi", "Pakistan")
            .enqueue(object : Callback<TimeByLocation> {
                override fun onFailure(call: Call<TimeByLocation>, t: Throwable) {
                    Log.d("PrayerTimingPresenter", t.localizedMessage)
                }

                override fun onResponse(call: Call<TimeByLocation>, response: Response<TimeByLocation>) {
                    Log.d("PrayerTimingPresenter", response.body()?.data.toString())
                    view.onDataLoaded(response.body()?.data!!)
                }
            })
    }

    private fun getCalenderTimingsFromServer() {
        MyApiInterface.create()
            .getPrayTimingCalendarByCity("Karachi", "Pakistan", 7, 2018)
            .enqueue(object : Callback<CalenderByCity> {

                override fun onFailure(call: Call<CalenderByCity>, t: Throwable) {
                    Log.d("PrayerTimingPresenter", t.localizedMessage)

                }

                override fun onResponse(call: Call<CalenderByCity>, response: Response<CalenderByCity>) {
                    Log.d("PrayerTimingPresenter", response.body()?.toString())

                    val data : List<Data> = response.body()!!.data

                    view.onCalenderDataLoaded(data)
                }
            })
    }

    override fun attach(view: PrayerTimingFragContract.View) {
        this.view = view
    }
}