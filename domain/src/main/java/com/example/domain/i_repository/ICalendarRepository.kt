package com.example.domain.i_repository

import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import kotlinx.coroutines.flow.Flow

interface ICalendarRepository {
    suspend fun findCalendar(): Flow<List<Calendar>>
    suspend fun updateIsSelected(job: Job)
}