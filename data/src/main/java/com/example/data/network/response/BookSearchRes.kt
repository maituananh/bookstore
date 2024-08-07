package com.example.data.network.response

import com.example.bookstore.api.response.BookRes
import com.example.domain.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchRes(
    @Json(name = "error") val error: Int?,
    @Json(name = "total") val total: Int?,
    @Json(name = "page") val page: Int?,
    @Json(name = "books") val bookRes: List<BookRes>?
) {
    fun toBookList(): List<Book> {
        return this.bookRes?.map {
            it.toBookData()
        } ?: emptyList()
    }
}