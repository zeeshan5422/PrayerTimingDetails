package com.zues.islamic.ui.monthlyTiming

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.R
import com.zues.islamic.di.component.DaggerFragmentComponent
import com.zues.islamic.models.Data
import com.zues.islamic.ui.main.MainActivity
import com.zues.islamic.ui.prayerTiming.MonthTimingsAdapter
import kotlinx.android.synthetic.main.fragment_monthly_timing.*
import javax.inject.Inject

/* ---  Created by akhtarz on 7/15/2019. ---*/
class MonthlyTimingFragment : BaseFragment(), MonthlyTimingFragmentContract.View {

    @Inject
    lateinit var presenter: MonthlyTimingFragmentContract.Presenter

    var data: Data? = null

    override val layoutId: Int
        get() = R.layout.fragment_monthly_timing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        setHasOptionsMenu(true)
        presenter.attach(this)
        presenter.subscribe()
        helper?.showLoading("Loading data")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.timing_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.nav_item_detail -> showDetail()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDetail() {
        helper?.showToast(msg = "Showing Detail")
        // show dialog
        //

        val timingDetailDialog: TimingDetailDialog? =
            context?.let {
                data?.let { it1 ->
                    TimingDetailDialog(context = it, timingDetail = it1)
                }
            }
        timingDetailDialog?.show()
    }

    override fun onTimingLoaded(item: List<Data>) {

        var (timings, date) = item.get(0)

        data = with(item) { get(0) }

        recyclerview_monthly.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerview_monthly.itemAnimator = DefaultItemAnimator()
        recyclerview_monthly.adapter = MonthTimingsAdapter(item)

        helper?.hideLoading()
    }

    override fun onError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        helper?.hideLoading()
    }

    private fun injectDependency() {
        val component = DaggerFragmentComponent.builder()
            .activityModule((activity as MainActivity).component)
            .build()
        component.inject(this)
    }
}