package com.example.bookstore.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.bookstore.R
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.retrofit.RetrofitHelper

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtSearch = view.findViewById<SearchView>(R.id.svFragmentSearch)

        txtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                RetrofitCallBack(
                    requireContext(), view, R.id.rvFragmentHome,
                    RetrofitHelper.getInstance().create(ItBookApi::class.java).search(query!!)
                ).reqCallBack()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}