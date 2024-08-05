package com.example.bookstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstore.adapter.PagerAdapter
import com.example.bookstore.databinding.ActivityHomeGridViewBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeGridViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vp2.adapter = PagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Home"
                }

                else -> {
                    tab.text = "Search"
                }
            }
        }.attach()

        binding.textViewHome.text = intent.getStringExtra("username")
    }
}