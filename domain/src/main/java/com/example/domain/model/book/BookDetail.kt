package com.example.domain.model.book

class BookDetail(
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
}