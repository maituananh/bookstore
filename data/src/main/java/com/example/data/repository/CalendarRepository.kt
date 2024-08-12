package com.example.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.network.CalendarServer
import com.example.data.network.setting.CalenderApi
import com.example.domain.i_repository.ICalendarRepository
import com.example.domain.model.calendar.Calendar
import retrofit2.Retrofit
import javax.inject.Inject

class CalendarRepository
@Inject constructor(@CalenderApi private val provideRetrofit: Retrofit) : ICalendarRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun findCalendar(): List<Calendar> {
        return provideRetrofit.create(CalendarServer::class.java).fetchCalendar().body()!!
            .map { it.toCalendar() }
    }
}