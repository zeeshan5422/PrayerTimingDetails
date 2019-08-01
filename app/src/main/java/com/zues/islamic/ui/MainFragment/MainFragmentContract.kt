package com.zues.islamic.ui.MainFragment

import com.zues.islamic.ui.base.BaseContract

/* ---  Created by akhtarz on 6/27/2019. ---*/
class MainFragmentContract {

    enum class Selected_Type{
        DATE, TIME
    }

    interface View : BaseContract.View {
        fun loadDateTimeFragment(name: MainFragmentContract.Selected_Type)
        fun loadPrayerTimingFragment()
        fun loadMontlhyTimingFragment()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadDateTimeData(name: Selected_Type)
        fun loadPrayerTimingData()
        fun loadMonthlyTimingData()
    }
}