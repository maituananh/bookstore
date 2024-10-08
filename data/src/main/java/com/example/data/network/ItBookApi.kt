package com.example.data.network

import com.example.data.network.response.BookDetailRes
import com.example.data.network.response.BookSearchRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItBookApi {

    @GET("new")
    suspend fun new():  Response<BookSearchRes>

    @GET("search/{search}")
    suspend fun search(@Path("search") search: String): Response<BookSearchRes>

    @GET("books/{isbn13}")
    suspend fun getByIsbn13(@Path("isbn13") isbn13: String): Response<BookDetailRes>
}