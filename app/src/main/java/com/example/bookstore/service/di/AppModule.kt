package com.example.bookstore.service.di

import com.example.bookstore.fragment.HomeFragment
import com.example.bookstore.service.BookService
import com.example.bookstore.service.IBookService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Retrofit

@Module
@InstallIn(HomeFragment::class)
object AppModule {

    @Binds
    fun provideBookService(provideRetrofit: Retrofit) : IBookService = BookService(provideRetrofit)
}