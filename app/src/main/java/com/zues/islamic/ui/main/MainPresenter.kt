package com.zues.islamic.ui.main


/* ---  Created by akhtarz on 6/27/2019. ---*/
class MainPresenter : MainActivityContract.Presenter {

    private lateinit var view : MainActivityContract.View

    override fun onDrawerAboutClick() {
        view.loadAboutFragment()
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
        view.loadMainFragment()
    }
}