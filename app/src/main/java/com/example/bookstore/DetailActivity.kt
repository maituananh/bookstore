package com.example.bookstore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstore.common.util.SetImageResource
import com.example.bookstore.databinding.ActivityDetailBinding
import com.example.bookstore.ui.detail.BookDetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val bookDetailViewModel: BookDetailViewModel by viewModels<BookDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isbn13: String = intent.getStringExtra("isbn13") ?: ""

        bookDetailViewModel.findById(isbn13)
        bookDetailViewModel.bookDetail.observe(this) {
            binding.txtTitleVal.text = it.title
            binding.txtAuthVal.text = it.authors
            binding.txtPriceVal.text = it.price
            binding.txtPublisherVal.text = it.publisher
            SetImageResource().setImage(this@DetailActivity, it.image, binding.imageView)
        }
    }
}