package com.example.data.network.response.calender

import com.squareup.moshi.Json

enum class JobStatusRes {
    @Json
    Missed,
    @Json
    Completed,
    @Json
    Assigned
}