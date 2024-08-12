package com.example.data.network.response.calender

import com.example.domain.model.calendar.Job
import com.example.domain.model.calendar.JobStatus
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobRes(
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: JobStatusRes,
    @Json(name = "exercises")
    val exercises: Int
) {
    fun toJob(): Job {
        val jobStatus: JobStatus = when (status) {
            JobStatusRes.Missed -> JobStatus.MISSED
            JobStatusRes.Completed -> JobStatus.COMPLETED
            JobStatusRes.Assigned -> JobStatus.ASSIGNED
        }

        return Job(name, jobStatus, exercises)
    }
}