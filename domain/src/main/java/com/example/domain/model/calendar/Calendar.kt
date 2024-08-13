package com.example.domain.model.calendar

import java.time.LocalDate

class Calendar (
    val date: LocalDate,
    val day: Int,
    val jobs: List<Job>,
) {
}