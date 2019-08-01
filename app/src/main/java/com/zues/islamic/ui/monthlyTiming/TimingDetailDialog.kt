package com.zues.islamic.ui.monthlyTiming

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.zues.islamic.R
import com.zues.islamic.models.Data
import kotlinx.android.synthetic.main.dialog_timing_detail.*

/* ---  Created by akhtarz on 7/18/2019. ---*/
class TimingDetailDialog constructor(context: Context) : Dialog(context) {

    var timingDetail: Data? = null

    constructor(context: Context, timingDetail: Data) : this(context) {
        this.timingDetail = timingDetail
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setCancelable(true)
        this.setCanceledOnTouchOutside(true)
        val view: Int = R.layout.dialog_timing_detail
        setContentView(view)
        initDialog()
    }

    fun initDialog() {
        with(timingDetail) {
            dateTxt.text = this?.date?.gregorian?.date
            dayTxt.text = this?.date?.gregorian?.day
            yearTxt.text = this?.date?.gregorian?.year
            weekDayTxt.text = this?.date?.gregorian?.weekday?.en
            monthTxt.text = this?.date?.gregorian?.month?.number
            monthNoTxt.text = this?.date?.gregorian?.month?.en

            hDateTxt.text = this?.date?.hijri?.date
            hDayTxt.text = this?.date?.hijri?.day
            hYearTxt.text = this?.date?.hijri?.year
            hWeekDayTxt.text = this?.date?.hijri?.weekday?.en
            hMonthTxt.text = this?.date?.hijri?.month?.en
            hMonthNoTxt.text = this?.date?.hijri?.month?.number

            timeZoneTxt.text = this?.meta?.timezone
        }

    }


}