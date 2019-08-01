package com.zues.islamic.data.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/* ---  Created by akhtarz on 6/28/2019. ---*/
@Entity(tableName = "Zone")
data class Zone(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val zone: String,
    val zoneCity: String
) {

}