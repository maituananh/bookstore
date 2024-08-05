package com.example.bookstore.action

import com.example.bookstore.model.Book

interface IRecyclerAction {
    fun onClick(position: Int)

    fun onClick(book: Book)
}