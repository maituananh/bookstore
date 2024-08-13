package com.example.data.network

import com.example.data.network.response.calender.CalendarRes
import retrofit2.Response
import retrofit2.http.GET

interface CalendarServer {

    @GET("c/2126-f485-4bb6-b447")
    suspend fun fetchCalendar():  Response<List<CalendarRes>>
}