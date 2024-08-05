package com.example.bookstore.service

import com.example.bookstore.model.Book
import com.example.bookstore.model.BookDetail

interface IBookService {
    suspend fun findById(isbn13: String): BookDetail
    suspend fun findNew(): List<Book>
}