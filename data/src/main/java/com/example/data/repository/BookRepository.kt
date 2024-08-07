package com.example.data.repository

import com.example.bookstore.api.response.BookDetailRes
import com.example.data.network.ItBookApi
import com.example.domain.model.Book
import com.example.domain.model.BookDetail
import com.example.domain.repository_interface.IBookRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class BookRepository @Inject constructor(private val provideRetrofit: Retrofit) : IBookRepository {

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

    override fun fetchNewBooks(): List<Book> {
//        CoroutineScope(Job() + Dispatchers.IO).launch {
        return provideRetrofit.create(ItBookApi::class.java).new().body()!!.toBookList()
//        }
    }
}
