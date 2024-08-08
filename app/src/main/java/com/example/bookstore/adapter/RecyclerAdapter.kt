package com.example.bookstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.common.util.SetImageResource
import com.example.domain.model.book.Book

class RecyclerAdapter<T>(
    val data: List<T>,
    val onClickItem: IRecyclerAction<T>,
    val layoutItem: Int,
    val binding: (holder: RecyclerAdapter<T>.ViewHolder, position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // thây thế đống này từ view model
        binding(holder, position)
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.txtBookName).text = data[position].title
            this.findViewById<TextView>(R.id.txtDescription).text =
                data[position].subtitle
            SetImageResource().setImage(
                context,
                data[position].image,
                this.findViewById(R.id.imageBook)
            )
        }

        holder.itemView.setOnClickListener {
            onClickItem.onClick(books[position])
        }
    }
}