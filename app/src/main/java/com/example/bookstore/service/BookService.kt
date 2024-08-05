package com.example.bookstore.service

import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.response.BookDetailRes
import com.example.bookstore.api.response.BookSearchRes
import com.example.bookstore.model.Book
import com.example.bookstore.model.BookDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BookService(private val provideRetrofit: Retrofit) : IBookService {

    override suspend fun findById(isbn13: String): BookDetail {
        var bookDetail: BookDetail? = null

        provideRetrofit.create(ItBookApi::class.java).getByIsbn13(isbn13).enqueue(object :
            Callback<BookDetailRes> {
            override fun onResponse(call: Call<BookDetailRes>, response: Response<BookDetailRes>) {
                bookDetail = response.body()!!.toBookDetail()
            }

            override fun onFailure(call: Call<BookDetailRes>, t: Throwable) {
                throw RuntimeException(t)
            }
        })

        return bookDetail!!
    }

    override suspend fun findNew(): List<Book> {
        var bookList: List<Book>? = null

        provideRetrofit.create(ItBookApi::class.java).new().enqueue(object : Callback<BookSearchRes> {
            override fun onResponse(call: Call<BookSearchRes>, response: Response<BookSearchRes>) {
                bookList = response.body()!!.toBookList()
            }

            override fun onFailure(call: Call<BookSearchRes>, t: Throwable) {
                throw RuntimeException(t)
            }
        })

        return bookList!!
    }
}