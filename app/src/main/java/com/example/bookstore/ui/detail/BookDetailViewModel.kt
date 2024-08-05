package com.example.bookstore.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.BookDetail
import com.example.bookstore.service.BookService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val bookService: BookService) : ViewModel() {
    private val _bookDetail = MutableLiveData<BookDetail>()
    val bookDetail: LiveData<BookDetail> = _bookDetail

    fun findById(isbn13: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _bookDetail.value = bookService.findById(isbn13)
        }
    }
}