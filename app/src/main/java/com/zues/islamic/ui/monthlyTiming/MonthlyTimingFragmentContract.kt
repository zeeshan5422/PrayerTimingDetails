package com.zues.islamic.ui.monthlyTiming

import com.zues.islamic.models.Data
import com.zues.islamic.ui.base.BaseContract

/* ---  Created by akhtarz on 7/15/2019. ---*/
class MonthlyTimingFragmentContract {

    interface View : BaseContract.View {
        fun onTimingLoaded(item: List<Data>)
        fun onError(errorMsg: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getMonthlyTiming()
    }
}