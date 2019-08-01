package com.zues.islamic.ui.MainFragment

import android.os.Bundle
import android.view.View
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.R
import com.zues.islamic.di.component.DaggerFragmentComponent
import com.zues.islamic.ui.dateTime.DateTimeFragment
import com.zues.islamic.ui.main.MainActivity
import com.zues.islamic.ui.monthlyTiming.MonthlyTimingFragment
import com.zues.islamic.ui.prayerTiming.PrayerTimingFragment
import com.zues.islamic.utils.TestClass
import kotlinx.android.synthetic.main.layout_main_fragment.*
import javax.inject.Inject

/* ---  Created by akhtarz on 6/27/2019. ---*/
class MainFragment : BaseFragment(), MainFragmentContract.View {

    override val layoutId: Int
        get() = R.layout.layout_main_fragment

    @Inject
    lateinit var presenter: MainFragmentContract.Presenter

    @Inject
    lateinit var testClass: TestClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initViews(view, savedInstanceState)
    }

    override fun initViews(parent: View, savedInstanceState: Bundle?) {

        btn_current_time.setOnClickListener {
            presenter.loadDateTimeData(MainFragmentContract.Selected_Type.TIME)
        }
        /*btn_current_date.setOnClickListener {
            Presenter.loadDateTimeData(MainFragmentContract.Selected_Type.DATE)
        }*/
        btn_get_prayer_time.setOnClickListener {
            presenter.loadPrayerTimingData()
        }
        btn_get_monthly_timing.setOnClickListener {
            presenter.loadMonthlyTimingData()
        }
        testClass.showLog(TAG + " : BaseFragment(), MainFragmentContract.View")
    }

    override fun loadDateTimeFragment(name: MainFragmentContract.Selected_Type) {
        helper!!.replaceFragment(DateTimeFragment(), false, true)
    }

    override fun loadPrayerTimingFragment() {
        helper!!.replaceFragment(PrayerTimingFragment(), false, true)
    }

    override fun loadMontlhyTimingFragment() {
        helper!!.replaceFragment(MonthlyTimingFragment(), false, true)
    }


    private fun injectDependency() {
        val component = DaggerFragmentComponent.builder()
            .activityModule((activity as MainActivity).component)
//            .fragmentModule(FragmentModule()).activityComponent((activity as MainActivity).component)
            .build()
        component.inject(this)
    }

    fun newInstance(): MainFragment {
        return MainFragment()
    }

    override fun getTitle(): String {
        return "MAIN"
    }

    companion object {
        val TAG = MainFragment::class.java.simpleName
    }
}