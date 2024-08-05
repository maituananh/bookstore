package com.example.bookstore.common.request_callback

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetrofitCallBack<T>(
    val api: Call<T>
) {

    abstract fun onResponseCustom(response: Response<T>)

    open fun onFailureCustom(call: Call<T>, t: Throwable) {
        Log.e("Error:::", "Error " + t.message)
    }

    fun run() {
        GlobalScope.launch {
            api.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    onResponseCustom(response)
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    onFailureCustom(call, t)
                }
            })
        }
    }
}