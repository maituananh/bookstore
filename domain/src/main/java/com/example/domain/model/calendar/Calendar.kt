package com.example.domain.model.calendar

import java.time.LocalDate

data class Calendar (
    val date: LocalDate?,
    val day: Int?,
    val jobs: List<Job>,
) {
}