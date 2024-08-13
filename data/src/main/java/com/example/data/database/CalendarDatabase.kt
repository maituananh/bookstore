package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.JobDao
import com.example.data.database.entity.JobEntity

@Database(entities = [JobEntity::class], version = 3, exportSchema = false)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}