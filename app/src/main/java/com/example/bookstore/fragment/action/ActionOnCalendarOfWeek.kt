package com.example.bookstore.fragment.action

import android.content.Context
import android.os.Build
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.common.util.colorList
import com.example.bookstore.view_model.CalendarOfWeekViewModel
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import java.time.LocalDate


class ActionOnCalendarOfWeek
    (
    private val context: Context,
    private val calendarViewModel: CalendarOfWeekViewModel
) : IRecyclerAction<Calendar> {

    private lateinit var llDayOfWeek: LinearLayout
    private lateinit var rvJob: RecyclerView
    private lateinit var txtDayOfWeek: TextView
    private lateinit var txtDayOfMonth: TextView

    companion object {
        const val START_NAME_OF_DAY: Int = 0
        const val END_NAME_OF_DAY: Int = 3
    }

    override fun onClick(t: Calendar) {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        llDayOfWeek = holder.itemView.findViewById(R.id.ll_day_of_week)
        rvJob = holder.itemView.findViewById(R.id.rv_job)
        txtDayOfWeek = holder.itemView.findViewById(R.id.txt_day_of_week)
        txtDayOfMonth = holder.itemView.findViewById(R.id.txt_day_of_month)

        attachJobOfDayLayout(data[position].jobs)
        setTextAndColorForDayOfWeekAndOfMonth(data[position])
        resizeLinearLayoutDayOfWeek(data[position].jobs)
    }

    private fun attachJobOfDayLayout(jobs: List<Job>) {
        val linearLayoutManager = LinearLayoutManager(
            rvJob.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvJob.apply {
            adapter =
                RecyclerAdapter(
                    jobs,
                    ActionOnJobOfDay(context, calendarViewModel),
                    R.layout.layout_job_of_day,
                )
            layoutManager = linearLayoutManager
            setRecycledViewPool(RecyclerView.RecycledViewPool())
            addOnItemTouchListener(ItemTouchListener())
        }
    }

    private fun resizeLinearLayoutDayOfWeek(jobData: List<Job>) {
        if (jobData.size > 1) {
            llDayOfWeek.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 610)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTextAndColorForDayOfWeekAndOfMonth(calendar: Calendar) {
        calendar.date?.let {
            txtDayOfWeek.text =
                it.dayOfWeek.toString().substring(START_NAME_OF_DAY, END_NAME_OF_DAY)
            txtDayOfMonth.text = it.dayOfMonth.toString()
        }

        if (calendar.date!!.isEqual(LocalDate.now())) {
            txtDayOfWeek.setTextColor(context.colorList(R.color.purple))
            txtDayOfMonth.setTextColor(context.colorList(R.color.purple))
        }
    }
}