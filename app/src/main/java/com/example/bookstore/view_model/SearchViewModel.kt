package com.example.bookstore.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.i_repository.IBookRepository
import com.example.domain.model.book.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val iBookRepository: IBookRepository) :
    ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun findById(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _books.value = iBookRepository.findBooks(id)
        }
    }
}