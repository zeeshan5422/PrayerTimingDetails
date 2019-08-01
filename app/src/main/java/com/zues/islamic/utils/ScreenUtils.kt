package com.zues.islamic.utils

import android.content.res.Resources

/* ---  Created by akhtarz on 7/18/2019. ---*/
object ScreenUtils {

    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }
}