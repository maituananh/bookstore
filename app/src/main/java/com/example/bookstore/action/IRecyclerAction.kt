package com.example.bookstore.action

import com.example.domain.model.book.Book

interface IRecyclerAction<T> {
    fun onClick(position: Int)

    fun onClick(book: T)
}