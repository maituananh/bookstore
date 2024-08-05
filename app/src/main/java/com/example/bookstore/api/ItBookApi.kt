package com.example.bookstore.api

import com.example.bookstore.api.response.BookDetailRes
import com.example.bookstore.api.response.BookSearchRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItBookApi {

    @GET("new")
    fun new(): Call<BookSearchRes>

    @GET("search/{search}")
    fun search(@Path("search") search: String): Call<BookSearchRes>

    @GET("books/{isbn13}")
    fun getByIsbn13(@Path("isbn13") isbn13: String): Call<BookDetailRes>
}