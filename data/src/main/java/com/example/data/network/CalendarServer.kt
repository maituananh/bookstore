package com.example.data.network

import com.example.data.network.response.calender.CalendarRes
import retrofit2.Response
import retrofit2.http.GET

interface CalendarServer {

    @GET("c/59d7-7423-4029-ba6e")
    suspend fun fetchCalendar():  Response<List<CalendarRes>>
}