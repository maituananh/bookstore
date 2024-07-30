package com.example.bookstore.common.request_callback

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.action.impl.RecyclerActionImpl
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.api.res.BookSearch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallBack(
    val context: Context,
    val view: View,
    val fragment: Int,
    val api: Call<BookSearch>
) {
    fun reqCallBack() {
        GlobalScope.launch {
            api.enqueue(object : Callback<BookSearch> {
                override fun onResponse(call: Call<BookSearch>, response: Response<BookSearch>) {
                    val adapter =
                        RecyclerAdapter(
                            response.body()!!,
                            RecyclerActionImpl(context),
                            context
                        )

                    val rvHome = view.findViewById<RecyclerView>(fragment)
                    rvHome.adapter = adapter
                    rvHome.layoutManager = GridLayoutManager(
                        context, 2, GridLayoutManager.VERTICAL,
                        false
                    )
                }

                override fun onFailure(call: Call<BookSearch>, t: Throwable) {
                    Log.e("Error:::", "Error " + t.message)
                }
            })
        }
    }
}