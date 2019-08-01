package com.zues.islamic.models

/* ---  Created by akhtarz on 7/4/2019. ---*/
class Timings(
    val Fajr: String,
    val Imsak: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String,
    val Midnight: String,
    val Sunrise: String,
    val Sunset: String
) {
    override fun toString(): String {
        return "  Fajr : $Fajr\n" +
                " Imsak : $Imsak\n" +
                " Dhuhr : $Dhuhr,\n" +
                " Asr : $Asr\n" +
                " Maghrib : $Maghrib\n" +
                " Isha : $Isha\n" +
                " Midnight : $Midnight\n" +
                "\n" +
                " Sunrise : $Sunrise\n" +
                " Sunset : $Sunset"
    }
}