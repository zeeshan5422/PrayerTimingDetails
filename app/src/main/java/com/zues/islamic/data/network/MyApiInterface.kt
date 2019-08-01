package com.zues.islamic.data.network

import com.zues.islamic.models.CalenderByCity
import com.zues.islamic.models.Time
import com.zues.islamic.models.TimeByLocation
import com.zues.islamic.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/* ---  Created by akhtarz on 6/27/2019. ---*/
interface MyApiInterface {

    @GET("currentTime")
    fun getTime(@Query("zone") selectedArea: String): Call<Time>

    @GET("currentDate")
    fun getDate(@Query("zone") selectedArea: String): Call<Time>

    @GET("timingsByCity")
    fun getPrayerTimingByAddress(@Query("city") city: String, @Query("country") country: String): Call<TimeByLocation>

    @GET("calendarByCity")
    fun getPrayTimingCalendarByCity(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Call<CalenderByCity>

    companion object Factory {
        fun create(): MyApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseURL)
                .build()
            return retrofit.create(MyApiInterface::class.java)
        }
    }
}