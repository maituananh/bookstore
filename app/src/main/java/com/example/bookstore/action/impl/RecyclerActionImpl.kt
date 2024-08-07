package com.example.bookstore.action.impl

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bookstore.DetailActivity
import com.example.bookstore.action.IRecyclerAction
import com.example.domain.model.book.Book

class RecyclerActionImpl(val context: Context) : IRecyclerAction {
    override fun onClick(position: Int) {
        Toast.makeText(context, "click on $position", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(book: Book) {
        val intentActivity= Intent(context, DetailActivity::class.java)
        intentActivity.putExtra("isbn13", book.isbn13)
        context.startActivity(intentActivity)
    }
}