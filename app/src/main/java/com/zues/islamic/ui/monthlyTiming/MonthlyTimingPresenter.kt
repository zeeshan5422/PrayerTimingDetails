package com.zues.islamic.ui.monthlyTiming

import android.util.Log
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.network.MyApiInterface
import com.zues.islamic.models.CalenderByCity
import com.zues.islamic.models.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* ---  Created by akhtarz on 7/15/2019. ---*/
class MonthlyTimingPresenter(val myDb: DB) : MonthlyTimingFragmentContract.Presenter {

    lateinit var view: MonthlyTimingFragmentContract.View;

    override fun getMonthlyTiming() {
        getCalenderTimingsFromServer()
    }

    override fun subscribe() {
        getMonthlyTiming()
    }

    override fun unSubscribe() {
    }

    override fun attach(view: MonthlyTimingFragmentContract.View) {
        this.view = view
    }


    private fun getCalenderTimingsFromServer() {
        MyApiInterface.create()
            .getPrayTimingCalendarByCity("Karachi", "Pakistan", 6, 2019)
            .enqueue(object : Callback<CalenderByCity> {

                override fun onFailure(call: Call<CalenderByCity>, t: Throwable) {
                    Log.d("PrayerTimingPresenter", t.localizedMessage)
                    view.onError(t.localizedMessage)

                }

                override fun onResponse(call: Call<CalenderByCity>, response: Response<CalenderByCity>) {
                    Log.d("PrayerTimingPresenter", response.body()?.toString())

                    val data : List<Data> = response.body()!!.data

                    view.onTimingLoaded(data)
                }
            })
    }

}