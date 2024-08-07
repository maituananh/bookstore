package com.example.bookstore.di

import com.example.bookstore.api.response.BookSearchRes
import retrofit2.Callback

interface ResponseCallBack<T> : Callback<T> {

}