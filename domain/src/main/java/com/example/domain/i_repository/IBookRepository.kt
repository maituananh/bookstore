package com.example.domain.i_repository

import com.example.domain.model.book.Book
import com.example.domain.model.book.BookDetail

interface IBookRepository {
    suspend fun findBooks(text: String): List<Book>
    suspend fun findNewBooks(): List<Book>
    suspend fun findById(id: String): BookDetail
}