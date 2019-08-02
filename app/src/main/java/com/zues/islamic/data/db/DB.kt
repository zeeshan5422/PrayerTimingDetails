package com.zues.islamic.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import com.zues.islamic.data.db.entities.Zone

/* ---  Created by akhtarz on 6/28/2019. ---*/

@Database(entities = arrayOf(Zone::class), version = 2, exportSchema = true)
abstract class DB : RoomDatabase() {

    abstract fun myDbDao(): MyDBInterface

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }

}