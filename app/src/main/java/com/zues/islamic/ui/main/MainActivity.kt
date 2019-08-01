package com.zues.islamic.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.raza.android.videocompressor.activities.BaseActivity
import com.zues.islamic.MyApp
import com.zues.islamic.R
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import com.zues.islamic.data.db.entities.Zone
import com.zues.islamic.di.component.ActivityComponent
import com.zues.islamic.di.component.AppComponent
import com.zues.islamic.di.component.DaggerActivityComponent
import com.zues.islamic.di.module.ActivityModule
import com.zues.islamic.ui.MainFragment.MainFragment
import com.zues.islamic.utils.TestClass
import javax.inject.Inject

/* ---  Created by akhtarz on 6/27/2019. ---*/
class MainActivity : BaseActivity(), MainActivityContract.View {

    override val layoutId = R.layout.activity_main

    lateinit var component: ActivityComponent

    @Inject
    lateinit var presenter: MainActivityContract.Presenter

    @Inject
    lateinit var myDb: DB

    @Inject
    lateinit var testClass: TestClass

    override fun initViews(savedInstanceState: Bundle?) {
        injectDependency()
        presenter.attach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.nav_item_info -> {
                presenter.onDrawerAboutClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadMainFragment() {
        addFragment(MainFragment(), false, true)
        setTitle(currentFragment?.getTitle())

        addDataInDB()

/*        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.layout_container, MainFragment().newInstance(), MainFragment.TAG)
            .commit()*/

    }

    override fun onFragmentBackStackChanged() {
        super.onFragmentBackStackChanged()
        setTitle(currentFragment?.getTitle())
    }

    override fun loadAboutFragment() {
        super.showToast("Load About Detail")
    }

    private fun injectDependency() {
        component = DaggerActivityComponent.builder()
            .appModule(MyApp.get(this).component!!) //applicationContext as MyApp).component!!)
//            .appComponent((applicationContext as MyApp).component)
            .build()
        component.inject(this)
    }

    private fun addDataInDB() {

    val list : ArrayList<Zone> = ArrayList()
    list.add(Zone(0, "Asia", "Karachi"))
    list.add(Zone(1, "Asia", "Hong_Kong"))
    list.add(Zone(2, "Asia", "Jakarta"))
    list.add(Zone(3, "Europe", "London"))
    list.add(Zone(4, "Europe", "Istanbul"))
    list.add(Zone(5, "Europe", "Paris"))
    list.add(Zone(6, "Indian", "Comoro"))
    list.add(Zone(7, "Indian", "Mauritius"))
    list.add(Zone(8, "Indian", "Reunion"))
    list.add(Zone(9, "America", "Chicago"))
    list.add(Zone(10, "America", "Costa_Rica"))
    list.add(Zone(11, "America", "Los_Angeles"))
    list.add(Zone(12, "Australia", "Sydney"))
    list.add(Zone(13, "Australia", "Melbourne"))

    // get db instance and add data in table

    Thread {
        myDb.myDbDao().insertZones(list)

        val ss : List<String> =  myDb.myDbDao().getZone()

        Log.d("ISLAMIC", ss.toString())
    }.start()
        showToast("Record inserted")

}

}