package com.zues.islamic

import android.app.Application
import android.content.Context
import android.util.Log
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.entities.Zone
import com.zues.islamic.di.component.AppComponent
import com.zues.islamic.di.component.DaggerAppComponent
import com.zues.islamic.di.module.AppModule
import com.zues.islamic.di.module.NetworkModule
import com.zues.islamic.di.module.RoomModule
import javax.inject.Inject

/* ---  Created by akhtarz on 6/27/2019. ---*/
class MyApp /*@Inject constructor()*/: Application() {

    internal var component: AppComponent? = null
        get() = field

    @Inject
    lateinit var db : DB

    override fun onCreate() {
        super.onCreate()

        setup()
//        addDataInDB()
    }

    private fun setup() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule())
            .build()
        component?.inject(this)

    }

    companion object MyFactory {
        operator fun get(context: Context): MyApp = context.applicationContext as MyApp

    }

    private fun addDataInDB() {

      /*  val list : ArrayList<Zone> = ArrayList()
        list.add(Zone(0, "Asia", "Karachi"))
        list.add(Zone(1, "Asia", "Hong_Kong"))
        list.add(Zone(2, "Asia", "jakarta"))
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
            db.myDbDao().insertZones(list)

            val ss : List<String> =  db.myDbDao().getZone()

            Log.d("MyApp", ss.toString())
        }.start()*/

    }


}