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

    private val homeFragments: List<Fragment> = listOf(
        HomeFragment(),
        SearchFragment(),
        CalendarOfWeekFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vp2.adapter = PagerAdapter(homeFragments, supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, position ->
            tab.text = homeFragments[position].toString()
        }.attach()

        binding.textViewHome.text = intent.getStringExtra("username")
    }
}