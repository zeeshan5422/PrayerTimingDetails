package com.zues.islamic.data.db

import android.arch.persistence.room.*
import com.zues.islamic.MyApp
import com.zues.islamic.data.db.entities.Zone
import javax.inject.Inject

/* ---  Created by akhtarz on 6/28/2019. ---*/
@Dao
interface MyDBInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertZones(zones: List<Zone>)

    @Query("SELECT DISTINCT zone from Zone")
    fun getZone(): List<String>


    @Query("SELECT DISTINCT zoneCity from Zone WHERE zone = :slectedZone")
    fun getCityByZone(slectedZone: String): List<String>

    companion object {
        fun createDB( context: MyApp): DB {
            return Room.databaseBuilder(
                context,
                DB::class.java, "zues.db"
            )
                .allowMainThreadQueries()
                .build()
        }

        fun createMyDBInterface(myDB: DB): MyDBInterface {
            return myDB.myDbDao()
        }
    }
}