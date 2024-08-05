package com.example.bookstore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.response.BookDetailRes
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.model.BookDetail
import com.example.bookstore.retrofit.RetrofitHelper
import retrofit2.Response

class BookDetailViewModel : ViewModel() {
    private val _bookDetail = MutableLiveData<BookDetail>()
    val bookDetail: LiveData<BookDetail> = _bookDetail

    fun findById(isbn13: String) {
        object : RetrofitCallBack<BookDetailRes>(
            RetrofitHelper.getInstance().create(ItBookApi::class.java).getByIsbn13(isbn13)
        ) {
            override fun onResponseCustom(response: Response<BookDetailRes>) {
                _bookDetail.value = response.body()!!.toBookDetail()
            }
        }.run()
    }
}