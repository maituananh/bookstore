package com.example.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.database.dao.JobDao
import com.example.data.database.entity.JobEntity
import com.example.data.network.CalendarServer
import com.example.data.network.setting.CalenderApi
import com.example.domain.i_repository.ICalendarRepository
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import retrofit2.Retrofit
import javax.inject.Inject

class CalendarRepository
@Inject constructor(
    @CalenderApi private val provideRetrofit: Retrofit,
    private val providesJobDao: JobDao
) : ICalendarRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun findCalendar(): List<Calendar> {
        val calendarListRes: List<Calendar> =
            provideRetrofit.create(CalendarServer::class.java).fetchCalendar().body()!!
                .map { it.toCalendar() }

        val jobListRes: List<Job> = calendarListRes.flatMap { it.jobs }.toList()

        val jobMap: Map<Int, JobEntity> = providesJobDao.getAllJobs().associateBy { it.id!! }

        jobListRes.map {
            if (jobMap.get(it.id) != null) {
                it.isSelected = jobMap.get(it.id)!!.isSelected
            } else {
                it.isSelected = 0
            }
        }

        return calendarListRes
    }
}