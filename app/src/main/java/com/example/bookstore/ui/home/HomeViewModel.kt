package com.example.bookstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Book
import com.example.domain.repository_interface.IBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val provideBookService:
    IBookRepository
) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun fetchBooks() {
//        viewModelScope.launch(Dispatchers.Main) {
        _bookList.value = provideBookService.fetchNewBooks()

//        }

    }
}