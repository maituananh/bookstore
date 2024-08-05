package com.example.bookstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.Book
import com.example.bookstore.service.BookService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel: ViewModel() {

    @Inject private lateinit var provideBookService: BookService
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _bookList.value = provideBookService.findNew()
        }
    }
}