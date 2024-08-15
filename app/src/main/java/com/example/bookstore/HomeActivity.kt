package com.example.bookstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bookstore.adapter.PagerAdapter
import com.example.bookstore.databinding.ActivityHomeGridViewBinding
import com.example.bookstore.fragment.CalendarOfWeekFragment
import com.example.bookstore.fragment.HomeFragment
import com.example.bookstore.fragment.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeGridViewBinding

    private val fragmentsMap : Map<Int, Pair<String, Fragment>> = mapOf(
        0 to Pair("Home", HomeFragment()),
        1 to Pair("Search", SearchFragment()),
        2 to Pair("Calendar", CalendarOfWeekFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vp2.adapter = PagerAdapter(fragmentsMap, supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, position ->
            tab.text = fragmentsMap[position]!!.first
        }.attach()

        binding.textViewHome.text = intent.getStringExtra("username")
    }
}