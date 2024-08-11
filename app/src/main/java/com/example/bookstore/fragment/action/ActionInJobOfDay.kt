package com.example.bookstore.fragment.action

import android.content.Context
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.domain.model.calendar.Calendar

class ActionInJobOfDay(
    private val parentHolder: RecyclerAdapter<Calendar>.ViewHolder,
    private val context: Context) : IRecyclerAction<Calendar> {
    override fun onClick(t: Calendar) {
        println("ActionInJobOfDay")
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
//        resizeDayOfWeek(data)

        val llJobOfDay = holder.itemView.findViewById<LinearLayout>(R.id.ll_job_of_day)
        llJobOfDay.setBackgroundColor(ContextCompat.getColor(context, R.color.cccccc))

        println("ActionInJobOfDay ${data[position]}")
    }

    private fun resizeDayOfWeek(data: List<Calendar>) {
        if (data.size <= 1) {
            val llDayOfWeek = parentHolder.itemView.findViewById<LinearLayout>(R.id.ll_day_of_week)
            val params = llDayOfWeek.layoutParams
            params.height = 100
            params.width = LayoutParams.MATCH_PARENT
            llDayOfWeek.layoutParams = params
        }
    }

    private fun layoutJobCompleted(holder: RecyclerAdapter<Calendar>.ViewHolder) {
        val llJobOfDay = holder.itemView.findViewById<LinearLayout>(R.id.ll_job_of_day)
        llJobOfDay.setBackgroundColor(ContextCompat.getColor(context, R.color.c_7470ef))

        val txtJobName = holder.itemView.findViewById<TextView>(R.id.txt_job_name)
        llJobOfDay.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

        val txtStatus = holder.itemView.findViewById<TextView>(R.id.txt_status)
        llJobOfDay.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
    }
}