package com.example.data.network.response.book

import com.example.domain.model.book.BookDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailRes(
    @Json(name = "error") val error: Int,
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "authors") val authors: String,
    @Json(name = "publisher") val publisher: String,
    @Json(name = "isbn10") val isbn10: String,
    @Json(name = "isbn13") val isbn13: String,
    @Json(name = "pages") val pages: Int,
    @Json(name = "year") val year: Int,
    @Json(name = "rating") val rating: Int,
    @Json(name = "desc") val desc: String,
    @Json(name = "price") val price: String,
    @Json(name = "image") val image: String,
    @Json(name = "url") val url: String,
    @Json(name = "pdf") var pdf: Any = ""
) {
    fun toBookDetail(): BookDetail = BookDetail(
        title = title,
        subtitle = subtitle,
        authors, publisher, isbn10, isbn13, pages, year, rating, desc, price, image, url, pdf
    )
}