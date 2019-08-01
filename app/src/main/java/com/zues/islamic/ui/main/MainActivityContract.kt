package com.zues.islamic.ui.main

import com.zues.islamic.ui.base.BaseContract

/* ---  Created by akhtarz on 6/27/2019. ---*/
class MainActivityContract {

    interface View :BaseContract.View{
        fun loadMainFragment()
        fun loadAboutFragment()
    }

    interface Presenter :BaseContract.Presenter<View>{
        fun onDrawerAboutClick()
    }
}