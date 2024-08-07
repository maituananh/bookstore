package com.example.bookstore.api.response

import com.example.domain.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookRes(
    @Json(name = "title")
    val title: String,
    @Json(name = "subtitle")
    val subtitle: String,
    @Json(name = "isbn13")
    val isbn13: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "url")
    val url: String
) {
    fun toBookData(): Book = Book(
        title = title,
        subtitle = subtitle,
        isbn13 = isbn13,
        price = price,
        image = image,
        url = url
    )
}
