package com.example.data.network.response

import com.example.domain.model.book.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookRes(
    @Json(name = "title")
    var title: String = "",
    @Json(name = "subtitle")
    var subtitle: String = "",
    @Json(name = "isbn13")
    var isbn13: String = "",
    @Json(name = "price")
    var price: String = "",
    @Json(name = "image")
    var image: String = "",
    @Json(name = "url")
    var url: String = ""
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
