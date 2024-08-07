package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.action.impl.RecyclerActionImpl
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentSearchBinding
import com.example.bookstore.view_model.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    val searchViewModel: SearchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventSearch()

        searchViewModel.books.observe(viewLifecycleOwner) {
            binding.rvFragmentSearch.adapter =
                RecyclerAdapter(it ?: emptyList(), RecyclerActionImpl(requireContext()))
        }
    }

    private fun eventSearch() {
        binding.svFragmentSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.findById(query!!)

                binding.rvFragmentSearch.run {
                    this.layoutManager = GridLayoutManager(
                        context, 2, GridLayoutManager.VERTICAL,
                        false
                    )
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}