package com.zues.islamic.models

/* ---  Created by akhtarz on 7/4/2019. ---*/
class Date(
    val readable: String,
    val timestamp: String,
    val gregorian: Gregorian,
    val hijri: Hijri
) {
    override fun toString(): String {
        return " readable : $readable\n" +
                "timestamp : $timestamp"
    }
}