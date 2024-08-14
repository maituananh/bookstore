package com.example.bookstore.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.i_repository.IBookRepository
import com.example.domain.model.book.BookDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val iBookRepository: IBookRepository) : ViewModel() {
    private val _bookDetail = MutableLiveData<BookDetail>()
    val bookDetail: LiveData<BookDetail> = _bookDetail

    fun findById(isbn13: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _bookDetail.value = iBookRepository.findById(isbn13)
        }
    }
}