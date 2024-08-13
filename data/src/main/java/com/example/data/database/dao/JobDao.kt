package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entity.JobEntity

@Dao
interface JobDao {

    @Query("SELECT * FROM `jobs`")
    fun getAllJobs(): List<JobEntity>

    @Query("SELECT * FROM `jobs` Where `id` in (:ids)")
    fun getAllJobsByIdIn(ids: List<Int>): List<JobEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobEntity)
}