package com.example.data.network.response.book

import com.example.domain.model.book.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchRes(
//    @Json(name = "error") var error: Int = 0,
//    @Json(name = "total") var total: Int = 0,
//    @Json(name = "page") var page: Int = 0,
    @Json(name = "books") var bookRes: List<BookRes> = emptyList()
) {
    fun toBookList(): List<Book> {
        return this.bookRes.map {
            it.toBookData()
        }
    }
}