package com.example.bookstore.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.DetailActivity
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.common.util.SetImageResource
import com.example.bookstore.databinding.FragmentSearchBinding
import com.example.bookstore.view_model.SearchViewModel
import com.example.domain.model.book.Book
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), IRecyclerAction<Book> {

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
                RecyclerAdapter(it ?: emptyList(), this, R.layout.layout_item)
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

    override fun onClick(book: Book) {
        val intentActivity = Intent(context, DetailActivity::class.java)
        intentActivity.putExtra(EXTRA_ISBN_1, book.isbn13)
        context?.startActivity(intentActivity)
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Book>.ViewHolder,
        position: Int,
        data: List<Book>
    ) {
        holder.itemView.apply {
            this.findViewById<TextView>(R.id.txt_book_name).text = data[position].title
            this.findViewById<TextView>(R.id.txt_description).text =
                data[position].subtitle
            SetImageResource().setImage(
                context,
                data[position].image,
                this.findViewById(R.id.image_book)
            )
        }
    }

    override fun toString(): String {
        return "Search"
    }
}