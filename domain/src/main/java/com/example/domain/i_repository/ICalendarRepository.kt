package com.example.domain.i_repository

import com.example.domain.model.calendar.Calendar

interface ICalendarRepository {
    suspend fun findCalendar(): List<Calendar>
}