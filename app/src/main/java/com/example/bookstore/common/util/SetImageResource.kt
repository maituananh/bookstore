package com.example.bookstore.common.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class SetImageResource {

    fun setImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }
}