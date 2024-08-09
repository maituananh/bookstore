//package com.example.bookstore.action.impl
//
//import android.content.Context
//import android.content.Intent
//import android.widget.Toast
//import com.example.bookstore.DetailActivity
//import com.example.bookstore.action.IRecyclerAction
//import com.example.bookstore.adapter.RecyclerAdapter
//import com.example.domain.model.book.Book
//
//class RecyclerActionImpl<T>(val context: Context) : IRecyclerAction<T> {
//    override fun onClick(position: Int) {
//        Toast.makeText(context, "click on $position", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun bindingDataToItemLayout(holder: RecyclerAdapter<T>.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onClick(book: T) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onClick(book: Book) {

//    }
//}