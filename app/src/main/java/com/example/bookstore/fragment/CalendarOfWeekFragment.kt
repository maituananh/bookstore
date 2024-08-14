package com.example.bookstore.fragment

import android.annotation.SuppressLint
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
import com.example.bookstore.common.util.colorList
import com.example.bookstore.databinding.FragmentCalendarOfWeekBinding
import com.example.bookstore.fragment.action.ActionInJobOfDay
import com.example.bookstore.fragment.action.ItemTouchListener
import com.example.bookstore.view_model.CalendarOfWeekViewModel
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class CalendarOfWeekFragment : Fragment(R.layout.fragment_calendar_of_week),
    IRecyclerAction<Calendar> {

    private lateinit var binding: FragmentCalendarOfWeekBinding
    private val calendarViewModel: CalendarOfWeekViewModel by viewModels<CalendarOfWeekViewModel>()

    private var llDayOfWeek: LinearLayout? = null
    private var rvJob: RecyclerView? = null
    private var txtDayOfWeek: TextView? = null
    private var txtDayOfMonth: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarOfWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarViewModel.loading.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

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
        llDayOfWeek = holder.itemView.findViewById(R.id.ll_day_of_week)
        rvJob = holder.itemView.findViewById(R.id.rvJob)
        txtDayOfWeek = holder.itemView.findViewById(R.id.txt_day_of_week)
        txtDayOfMonth = holder.itemView.findViewById(R.id.txt_day_of_month)

        attachJobOfDayLayout(data[position].jobs)
        setTextAndColorForDayOfWeekAndOfMonth(data[position])
        resizeLinearLayoutDayOfWeek(data[position].jobs)
    }

    private fun attachJobOfDayLayout(jobs: List<Job>) {
        val linearLayoutManager = LinearLayoutManager(
            rvJob!!.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvJob!!.apply {
            adapter =
                RecyclerAdapter(
                    jobs,
                    ActionInJobOfDay(requireContext(), calendarViewModel),
                    R.layout.layout_job_of_day,
                )
            layoutManager = linearLayoutManager
            setRecycledViewPool(RecyclerView.RecycledViewPool())
            addOnItemTouchListener(ItemTouchListener())
        }
    }

    private fun resizeLinearLayoutDayOfWeek(jobData: List<Job>) {
        if (jobData.size > 1) {
            llDayOfWeek!!.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 610)
        }
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTextAndColorForDayOfWeekAndOfMonth(calendar: Calendar) {
        calendar.date?.let {
            txtDayOfWeek!!.text = it.dayOfWeek.toString().substring(0, 3)
            txtDayOfMonth!!.text = it.dayOfMonth.toString()
        }

        if (calendar.date!!.isEqual(LocalDate.now())) {
            txtDayOfWeek!!.setTextColor(requireContext().colorList(R.color.c_7470ef))
            txtDayOfMonth!!.setTextColor(requireContext().colorList(R.color.c_7470ef))
        }
    }
}