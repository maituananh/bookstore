package com.example.domain.model.calendar

class Job(
    val id: Int,
    val name: String,
    val status: JobStatus,
    val exercises: Int,
    var isSelected: Int = 0
) {
}