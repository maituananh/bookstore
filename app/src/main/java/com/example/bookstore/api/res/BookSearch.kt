package com.example.bookstore.api.res

data class BookSearch(
    val error: Int,
    val total: Int,
    val page: Int,
    val books: List<Book>
) {
}