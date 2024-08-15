package com.example.bookstore.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    private val fragmentsMap: Map<Int, Pair<String, Fragment>>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return fragmentsMap.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsMap[position]!!.second
    }
}