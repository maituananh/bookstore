package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.action.impl.RecyclerActionImpl
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.fetchBooks()

        viewModel.bookList.observe(viewLifecycleOwner) {
            binding.rvFragmentHome.adapter =
                RecyclerAdapter(it, RecyclerActionImpl(requireContext()))
            binding.rvFragmentHome.layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL,
                false
            )
        }
    }
}