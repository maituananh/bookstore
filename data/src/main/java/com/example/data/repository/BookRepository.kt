package com.example.data.repository

import com.example.data.network.ItBookServer
import com.example.data.network.setting.ItBookApi
import com.example.domain.i_repository.IBookRepository
import com.example.domain.model.book.Book
import com.example.domain.model.book.BookDetail
import retrofit2.Retrofit
import javax.inject.Inject

class BookRepository
@Inject constructor(@ItBookApi private val provideRetrofit: Retrofit) : IBookRepository {

    private val itBookServer = provideRetrofit.create(ItBookServer::class.java)

    override suspend fun findBooks(text: String): List<Book> {
        return itBookServer.search(text).body()!!.toBookList()
    }

    override suspend fun findNewBooks(): List<Book> {
        return itBookServer.new().body()!!.toBookList()
    }

    override suspend fun findById(id: String): BookDetail {
        return itBookServer.getByIsbn13(id).body()!!.toBookDetail()
    }
}