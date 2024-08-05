package com.example.bookstore.api.response

import com.example.bookstore.model.Book
import com.google.gson.annotations.SerializedName

data class BookSearchRes(
    @SerializedName("error") val error: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("books") val bookRes: List<BookRes>
) {
    fun toBookList(): List<Book> {
        return this.bookRes.map {
            it.toBookData()
        }
    }
}