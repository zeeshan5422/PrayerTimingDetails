package com.zues.islamic.ui.prayerTiming

import com.zues.islamic.models.CalenderByCity
import com.zues.islamic.models.CalenderData
import com.zues.islamic.models.Data
import com.zues.islamic.models.TimeByLocation
import com.zues.islamic.ui.base.BaseContract

/* ---  Created by akhtarz on 7/4/2019. ---*/
class PrayerTimingFragContract {

    interface View : BaseContract.View{
        fun onDataLoaded(data: Data)
        fun onCalenderDataLoaded(data: List<Data>)

    }
    interface Presenter  :BaseContract.Presenter<View> {

    }

}