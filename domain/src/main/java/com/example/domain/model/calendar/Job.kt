package com.example.domain.model.calendar

data class Job(
    val id: Int,
    val name: String,
    val status: JobStatus,
    val exercises: Int,
    var isSelected: Boolean = false
) {
}