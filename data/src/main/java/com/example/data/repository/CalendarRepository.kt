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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class CalendarRepository
@Inject constructor(
    @CalenderApi private val provideRetrofit: Retrofit,
    private val providesJobDao: JobDao
) : ICalendarRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun findCalendar(): Flow<List<Calendar>> = flow {
        val calendarListRes: List<Calendar> =
            provideRetrofit.create(CalendarServer::class.java).fetchCalendar().body()!!
                .map { it.toCalendar() }

        val jobListRes: List<Job> = calendarListRes.flatMap { it.jobs }.toList()

        val jobMap: Map<Int, JobEntity> = providesJobDao.getAllJobs().associateBy { it.jobId }

        jobListRes.map {
            if (jobMap[it.id] != null) {
                it.isSelected = jobMap[it.id]!!.isSelected
            } else {
                it.isSelected = false
            }
        }

        emit(calendarListRes)
    }

    override suspend fun updateIsSelected(job: Job) {
        val jobEntity = providesJobDao.getJobById(job.id)

        if (jobEntity == null) {
            providesJobDao.insertJob(JobEntity(null, job.id, true))
        } else {
            providesJobDao.updateJob(JobEntity(jobEntity.id, job.id, job.isSelected))
        }
    }
}