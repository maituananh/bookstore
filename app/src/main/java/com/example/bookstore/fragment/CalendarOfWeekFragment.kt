package com.example.bookstore.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentCalendarOfWeekBinding
import com.example.bookstore.fragment.action.ActionInJobOfDay
import com.example.bookstore.fragment.action.ItemTouchListener
import com.example.bookstore.view_model.CalendarOfWeekViewModel
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarOfWeekFragment : Fragment(R.layout.fragment_calendar_of_week),
    IRecyclerAction<Calendar> {

    private lateinit var binding: FragmentCalendarOfWeekBinding
    private val calendarViewModel: CalendarOfWeekViewModel by viewModels<CalendarOfWeekViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarOfWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarViewModel.fetchCalendar()

        calendarViewModel.calendarList.observe(viewLifecycleOwner) {
            binding.rvFragmentCalendar.adapter =
                RecyclerAdapter(it, this, R.layout.layout_day_of_week)
        }
        binding.rvFragmentCalendar.layoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun onClick(t: Calendar) {
        Toast.makeText(requireContext(), "onClick123", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        resizeLinearLayoutDayOfWeek(holder, data[position].jobs)

        val txtDayOfWeek: TextView = holder.itemView.findViewById(R.id.txt_day_of_week)
        val txtDayOfMonth: TextView = holder.itemView.findViewById(R.id.txt_day_of_month)

        txtDayOfWeek.text = data[position].date.dayOfWeek.toString().substring(0, 3)
        txtDayOfMonth.text = data[position].date.dayOfMonth.toString()

        attachJobOfDayLayout(holder, position, data)
    }

    private fun attachJobOfDayLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        val rvJob: RecyclerView = holder.itemView.findViewById(R.id.rvJob)
        val linearLayoutManager = LinearLayoutManager(
            rvJob.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvJob.apply {
            adapter =
                RecyclerAdapter(
                    data[position].jobs,
                    ActionInJobOfDay(requireContext()),
                    R.layout.layout_job_of_day,
                )
            layoutManager = linearLayoutManager
            setRecycledViewPool(RecyclerView.RecycledViewPool())
            addOnItemTouchListener(ItemTouchListener())
        }
    }

    private fun resizeLinearLayoutDayOfWeek(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        jobData: List<Job>
    ) {
        if (jobData.size > 1) {
            val llDayOfWeek: LinearLayout = holder.itemView.findViewById(R.id.ll_day_of_week)
            llDayOfWeek.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 700)
            return
        }

        val llDayOfWeek: LinearLayout = holder.itemView.findViewById(R.id.ll_day_of_week)
        llDayOfWeek.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 370)
    }
}