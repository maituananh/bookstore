package com.example.bookstore.api.response

import com.example.bookstore.model.Book

data class BookRes(
    val title: String,
    val subtitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
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
