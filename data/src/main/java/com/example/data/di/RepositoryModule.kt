package com.example.data.di

import com.example.data.network.setting.CalenderApi
import com.example.data.network.setting.ItBookApi
import com.example.data.repository.BookRepository
import com.example.data.repository.CalendarRepository
import com.example.data.repository.UserRepository
import com.example.domain.i_repository.IBookRepository
import com.example.domain.i_repository.ICalendarRepository
import com.example.domain.i_repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(@ItBookApi provideRetrofit: Retrofit): IBookRepository =
        BookRepository(provideRetrofit)

    @Provides
    @Singleton
    fun provideCalendarRepository(@CalenderApi provideCalendarRetrofit: Retrofit): ICalendarRepository =
        CalendarRepository(provideCalendarRetrofit)

    @Provides
    @Singleton
    fun provideUserRepository(): IUserRepository = UserRepository()
}