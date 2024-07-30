package com.example.bookstore

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bookstore.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_grid_view)

        var vp2 = findViewById<ViewPager2>(R.id.vp2)
        vp2.adapter = PagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(findViewById(R.id.tlMenu), vp2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Home"
                }

                else -> {
                    tab.text = "Search"
                }
            }
        }.attach()

        val intentData = intent.getStringExtra("username")

        val textViewHome = findViewById<TextView>(R.id.textViewHome)
        textViewHome.text = intentData
    }
}