package com.example.bookstore.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bookstore.R
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.retrofit.RetrofitHelper

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        RetrofitCallBack(
            requireContext(), view, R.id.rvFragmentHome,
            RetrofitHelper.getInstance().create(ItBookApi::class.java).new()
        ).reqCallBack()
    }
}