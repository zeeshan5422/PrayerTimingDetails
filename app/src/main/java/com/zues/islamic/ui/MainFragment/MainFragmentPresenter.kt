package com.zues.islamic.ui.MainFragment

class MainFragmentPresenter : MainFragmentContract.Presenter {

    lateinit var view: MainFragmentContract.View

    override fun loadDateTimeData(name: MainFragmentContract.Selected_Type) {
        view.loadDateTimeFragment(name)
    }

    override fun loadPrayerTimingData() {
        view.loadPrayerTimingFragment()
    }

    override fun loadMonthlyTimingData() {
        view.loadMontlhyTimingFragment()
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun attach(view: MainFragmentContract.View) {
        this.view = view
    }

}
