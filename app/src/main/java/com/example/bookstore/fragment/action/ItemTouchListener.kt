package com.example.bookstore.fragment.action

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class ItemTouchListener : RecyclerView.OnItemTouchListener {
    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_MOVE -> {
                rv.parent.requestDisallowInterceptTouchEvent(true)
            }
        }
        return false
    }
}