package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.svFragmentSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                eventSearch(query!!)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//        })
    }

    private fun eventSearch(query: String) {
//        object :
//            RetrofitCallBack<BookSearchRes>(
//                RetrofitModule.getInstance().create(ItBookApi::class.java).search(query)
//            ) {
//            override fun onResponseCustom(response: Response<BookSearchRes>) {
//                val adapter =
//                    RecyclerAdapter(
//                        response.body()!!.toBookList(),
//                        RecyclerActionImpl(requireContext())
//                    )
//
//                binding.rvFragmentSearch.run {
//                    this.adapter = adapter
//                    this.layoutManager = GridLayoutManager(
//                        context, 2, GridLayoutManager.VERTICAL,
//                        false
//                    )
//                }
//            }
//        }.run()
    }
}