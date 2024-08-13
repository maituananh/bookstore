package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.CalendarDatabase
import com.example.data.database.dao.JobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesCalendarDatabase(@ApplicationContext context: Context): CalendarDatabase =
        Room.databaseBuilder(context, CalendarDatabase::class.java, "calendar")
            .allowMainThreadQueries().build()

    @Provides
    fun providesJobDao(database: CalendarDatabase): JobDao = database.jobDao()
}