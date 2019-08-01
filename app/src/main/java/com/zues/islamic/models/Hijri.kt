package com.zues.islamic.models

/* ---  Created by akhtarz on 7/19/2019. ---*/
data class Hijri constructor(
    val date: String,
    val day: String,
    val designation: Designation,
    val format: String,
//    val holidays: Any,
    val month: HijriMonth,
    val weekday: HijriWeekday,
    val year: String
)

data class HijriWeekday constructor(
    val en: String,
    val ar: String
)

data class HijriMonth constructor(
    val en: String,
    val ar: String,
    val number: String
)