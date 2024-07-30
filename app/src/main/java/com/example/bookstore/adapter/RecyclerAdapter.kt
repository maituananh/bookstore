package com.example.bookstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.api.res.BookSearch


class RecyclerAdapter(
    val bookSearch: BookSearch,
    val onClickItem: IRecyclerAction,
    val context: Context
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookSearch.books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.txtBookName).text = bookSearch.books[position].title
            this.findViewById<TextView>(R.id.txtDescription).text =
                bookSearch.books[position].subtitle
            setImage(bookSearch.books[position].image, this.findViewById(R.id.imageBook))
        }

        holder.itemView.setOnClickListener {
            onClickItem.onClick(position)
        }
    }

    private fun setImage(url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
//            .apply(
//                RequestOptions()
//                    .placeholder(R.drawable.placeholder_image) // Placeholder image
//                    .error(R.drawable.error_image) // Error image in case of loading failure
//            )
            .into(imageView)
    }
}