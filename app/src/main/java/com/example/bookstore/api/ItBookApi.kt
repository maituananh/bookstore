package com.example.bookstore.api

import com.example.bookstore.api.res.BookSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItBookApi {

    @GET("new")
    fun new(): Call<BookSearch>

    @GET("search/{search}")
    fun search(@Path("search") search: String): Call<BookSearch>
}