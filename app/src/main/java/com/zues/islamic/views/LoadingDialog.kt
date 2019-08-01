package com.zues.islamic.views

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.zues.islamic.R
import kotlinx.android.synthetic.main.progress_spinner.*

/* ---  Created by akhtarz on 7/15/2019. ---*/
class LoadingDialog constructor(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_spinner)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setMessage(msg: String) {
        message.text = msg
    }
}