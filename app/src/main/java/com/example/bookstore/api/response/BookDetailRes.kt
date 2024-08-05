package com.example.bookstore.api.response

import com.example.bookstore.model.BookDetail

data class BookDetailRes(
    val error: Int,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val isbn10: String,
    val isbn13: String,
    val pages: Int,
    val year: Int,
    val rating: Int,
    val desc: String,
    val price: String,
    val image: String,
    val url: String,
    val pdf: Any
) {
    fun toBookDetail(): BookDetail = BookDetail(
        title = title,
        subtitle = subtitle,
        authors, publisher, isbn10, isbn13, pages, year, rating, desc, price, image, url, pdf
    )
}