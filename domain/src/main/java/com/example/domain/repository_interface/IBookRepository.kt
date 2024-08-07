package com.example.domain.repository_interface

import com.example.domain.model.Book
import com.example.domain.model.BookDetail

interface IBookRepository {
    suspend fun findById(isbn13: String): BookDetail
    fun fetchNewBooks(): List<Book>
}