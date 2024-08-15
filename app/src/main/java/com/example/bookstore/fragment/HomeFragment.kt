package com.example.bookstore.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.DetailActivity
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.common.util.SetImageResource
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.view_model.HomeViewModel
import com.example.domain.model.book.Book
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), IRecyclerAction<Book> {

    companion object {
        const val EXTRA_ISBN_1 = "isbn13"
    }

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.fetchBooks()

        homeViewModel.bookList.observe(viewLifecycleOwner) {
            binding.rvFragmentHome.adapter =
                RecyclerAdapter(it, this, R.layout.layout_item)
            binding.rvFragmentHome.layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL,
                false
            )
        }
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

}