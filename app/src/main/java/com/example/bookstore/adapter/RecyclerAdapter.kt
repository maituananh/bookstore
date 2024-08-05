package com.example.bookstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.api.response.BookSearchRes
import com.example.bookstore.common.util.SetImageResource
import com.example.bookstore.model.Book


class RecyclerAdapter(
    val books: List<Book>,
    val onClickItem: IRecyclerAction,
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.txtBookName).text = books[position].title
            this.findViewById<TextView>(R.id.txtDescription).text =
                books[position].subtitle
            SetImageResource().setImage(
                context,
                books[position].image,
                this.findViewById(R.id.imageBook)
            )
        }

        holder.itemView.setOnClickListener {
            onClickItem.onClick(books[position])
        }
    }
}