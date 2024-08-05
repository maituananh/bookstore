package com.example.bookstore.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.response.BookSearchRes
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.model.Book
import com.example.bookstore.retrofit.RetrofitHelper
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun fetchBooks() {
        object : RetrofitCallBack<BookSearchRes>(
            RetrofitHelper.getInstance().create(ItBookApi::class.java).new()
        ) {
            override fun onResponseCustom(response: Response<BookSearchRes>) {
                _bookList.value = response.body()!!.toBookList()
            }
        }.run()
    }
}