package com.example.data.repository

import com.example.data.network.ItBookApi
import com.example.domain.i_repository.IBookRepository
import com.example.domain.model.book.Book
import com.example.domain.model.book.BookDetail
import retrofit2.Retrofit
import javax.inject.Inject

class BookRepository
@Inject constructor(private val provideRetrofit: Retrofit) : IBookRepository {

    override suspend fun findBooks(text: String): List<Book> {
        return provideRetrofit.create(ItBookApi::class.java).search(text).body()!!.toBookList()
    }

    override suspend fun findNewBooks(): List<Book> {
        return provideRetrofit.create(ItBookApi::class.java).new().body()!!.toBookList()
    }

    override suspend fun findById(id: String): BookDetail {
        return provideRetrofit.create(ItBookApi::class.java).getByIsbn13(id).body()!!.toBookDetail()
    }
}