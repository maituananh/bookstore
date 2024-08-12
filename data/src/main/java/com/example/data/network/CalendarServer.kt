package com.example.data.network

import com.example.data.network.response.calender.CalendarRes
import retrofit2.Response
import retrofit2.http.GET

interface CalendarServer {

    @GET("c/5ecf-c56d-4d63-8a56")
    suspend fun fetchCalendar():  Response<List<CalendarRes>>
}