package com.zues.islamic.models

/* ---  Created by akhtarz on 7/4/2019. ---*/
data class Meta(
    val latitude: String,
    val latitudeAdjustmentMethod: String,
    val longitude: String,
    val midnightMode: String,
    val school: String,
    val timezone: String
//    val method: String,
//    val offset: String,
) {
    override fun toString(): String {
        return " latitude : $latitude\n" +
                "latitudeAdjustmentMethod : $latitudeAdjustmentMethod\n" +
                "longitude : $longitude\n" +
                "midnightMode : $midnightMode\n" +
                "school : $school\n" +
                "timezone : $timezone"
    }
}