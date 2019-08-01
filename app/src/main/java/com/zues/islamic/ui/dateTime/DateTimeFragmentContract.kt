package com.zues.islamic.ui.dateTime

import com.zues.islamic.ui.base.BaseContract

/* ---  Created by akhtarz on 6/28/2019. ---*/
class DateTimeFragmentContract {

    interface View : BaseContract.View {
        fun onZoneLoaded(zones: List<String>)
        fun onZoneCitiesLoaded(cities: List<String>)
        fun onGetTime(time: String)
        fun onGetError(errorMsg: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadZone()
        fun loadCity(zone: String)
        fun getTime(zone: String, city: String)
    }
}