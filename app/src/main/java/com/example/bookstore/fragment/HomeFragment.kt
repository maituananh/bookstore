package com.example.bookstore.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.action.impl.RecyclerActionImpl
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.res.BookSearch
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        HandleCallBackBody(
            view,
            requireContext(),
            RetrofitHelper.getInstance().create(ItBookApi::class.java).new()
        ).run()
    }

    class HandleCallBackBody(private val view: View, context: Context, api: Call<BookSearch>) :
        RetrofitCallBack<BookSearch>(
            context, api
        ) {

        override fun onResponseCustom(response: Response<BookSearch>) {
            val adapter =
                RecyclerAdapter(
                    response.body()!!,
                    RecyclerActionImpl(context),
                    context
                )

            val rvHome = view.findViewById<RecyclerView>(R.id.rvFragmentHome)
            rvHome.adapter = adapter
            rvHome.layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL,
                false
            )
        }
    }
}