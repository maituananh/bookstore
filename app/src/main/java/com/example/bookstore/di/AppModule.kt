package com.example.bookstore.di

import com.example.bookstore.service.BookService
import com.example.bookstore.service.IBookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookService(provideRetrofit: Retrofit) : IBookService {
        return BookService(provideRetrofit)
    }

}