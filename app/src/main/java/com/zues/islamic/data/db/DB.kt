package com.zues.islamic.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.zues.islamic.data.db.entities.Zone
import dagger.Binds
import javax.inject.Inject

/* ---  Created by akhtarz on 6/28/2019. ---*/

@Database( entities = arrayOf(Zone::class), version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {

    abstract fun myDbDao() : MyDBInterface

}