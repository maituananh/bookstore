package com.example.bookstore.action

import com.example.bookstore.adapter.RecyclerAdapter

interface IRecyclerAction<T> {
    fun onClick(t: T)
    fun bindingDataToItemLayout(holder: RecyclerAdapter<T>.ViewHolder, position: Int, data: List<T>)
}