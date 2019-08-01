package com.zues.islamic.models

/* ---  Created by akhtarz on 7/19/2019. ---*/
data class Gregorian constructor(
    val date: String,
    val day: String,
    val year: String,
    val month: Month,
    val weekday: Weekday,
    val designation: Designation
)

data class Weekday constructor(
    val en: String
)

data class Month constructor(
    val en: String,
    val number: String
)

data class Designation constructor(
    val abbreviated: String,
    val expanded: String
)