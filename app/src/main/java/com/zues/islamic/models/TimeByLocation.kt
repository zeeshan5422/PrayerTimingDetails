package com.zues.islamic.models

/* ---  Created by akhtarz on 7/4/2019. ---*/
data class Data constructor(
    val timings: Timings,
    val date: Date,
    val meta: Meta
)

class TimeByLocation constructor(val data: Data)