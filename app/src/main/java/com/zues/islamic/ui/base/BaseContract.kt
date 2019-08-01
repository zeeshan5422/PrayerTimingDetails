package com.zues.islamic.ui.base

/* ---  Created by akhtarz on 6/27/2019. ---*/
class BaseContract {


    interface View {
    }

    interface Presenter<in T> {
        fun subscribe()
        fun unSubscribe()
        fun attach(view: T)
    }
}