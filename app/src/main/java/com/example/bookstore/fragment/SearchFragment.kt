package com.example.bookstore.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
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
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtSearch = view.findViewById<SearchView>(R.id.svFragmentSearch)

        txtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                eventSearch(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun eventSearch(query: String): Unit {
        object :
            RetrofitCallBack<BookSearch>(
                requireContext(),
                RetrofitHelper.getInstance().create(ItBookApi::class.java).search(query)
            ) {
            override fun onResponseCustom(response: Response<BookSearch>) {
                val adapter =
                    RecyclerAdapter(
                        response.body()!!,
                        RecyclerActionImpl(context),
                        context
                    )

                val rvSearch = view?.findViewById<RecyclerView>(R.id.rvFragmentSearch)
                rvSearch?.let {
                    rvSearch.adapter = adapter
                    rvSearch.layoutManager = GridLayoutManager(
                        context, 2, GridLayoutManager.VERTICAL,
                        false
                    )
                }
            }
        }.run()
    }
}