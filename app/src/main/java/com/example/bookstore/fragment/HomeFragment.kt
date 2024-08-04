package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.action.impl.RecyclerActionImpl
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.api.ItBookApi
import com.example.bookstore.api.res.BookSearch
import com.example.bookstore.common.request_callback.RetrofitCallBack
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.retrofit.RetrofitHelper
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        object : RetrofitCallBack<BookSearch>(
            requireContext(),
            RetrofitHelper.getInstance().create(ItBookApi::class.java).new()
        ) {
            override fun onResponseCustom(response: Response<BookSearch>) {
                val adapter =
                    RecyclerAdapter(
                        response.body()!!,
                        RecyclerActionImpl(context),
                        context
                    )
                binding.rvFragmentHome.adapter = adapter
                binding.rvFragmentHome.layoutManager = GridLayoutManager(
                    context, 2, GridLayoutManager.VERTICAL,
                    false
                )
            }
        }.run()
    }
}