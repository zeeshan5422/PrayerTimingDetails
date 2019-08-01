package com.zues.islamic.utils

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.InvalidationTracker
import android.util.Log
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import javax.inject.Inject

/* ---  Created by akhtarz on 7/3/2019. ---*/
class TestClass @Inject constructor() {
    public fun showLog(str: String) {
        Log.d("TEST_CLASS", str)
    }
}