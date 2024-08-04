package com.example.bookstore

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.res.BookDetail
import com.example.bookstore.common.util.SetImageResource
import com.example.bookstore.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val isbn13 = intent.getStringExtra("isbn13")

        GlobalScope.launch {
            val api =
                RetrofitHelper.getInstance().create(ItBookApi::class.java).getByIsbn13(isbn13!!)
            api.enqueue(object : Callback<BookDetail> {
                override fun onResponse(call: Call<BookDetail>, response: Response<BookDetail>) {
                    response.run {
                        val book: BookDetail = response.body()!!

                        val image: ImageView = findViewById(R.id.imageView)
                        SetImageResource().setImage(this@DetailActivity, book.image, image)

                        val txtPriceVal = findViewById<TextView>(R.id.txtPriceVal)
                        txtPriceVal.text = book.price

                        //                        val txtRatingVal = findViewById<TextView>(R.id.txtRatingVal)
                        //                        txtRatingVal.text = book.rating

                        val txtAuthVal = findViewById<TextView>(R.id.txtAuthVal)
                        txtAuthVal.text = book.authors

                        val txtTitleVal = findViewById<TextView>(R.id.txtTitleVal)
                        txtTitleVal.text = book.title

                        val txtPublisherVal = findViewById<TextView>(R.id.txtPublisherVal)
                        txtPublisherVal.text = book.publisher
                    }
                }

                override fun onFailure(call: Call<BookDetail>, t: Throwable) {
                    Log.e("Error:::", "Error " + t.message)
                }
            })
        }
    }
}