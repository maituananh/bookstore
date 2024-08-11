package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentCalendarOfWeekBinding
import com.example.bookstore.fragment.action.ActionInJobOfDay
import com.example.domain.model.calendar.Calendar


class CalendarOfWeekFragment : Fragment(R.layout.fragment_calendar_of_week),
    IRecyclerAction<Calendar> {

    private lateinit var binding: FragmentCalendarOfWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarOfWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val llDayOfWeek: LinearLayout = view.findViewById(R.id.ll_day_of_week)
//        val params = llDayOfWeek.layoutParams
//        params.height = 100
//        params.width = LayoutParams.MATCH_PARENT
//        llDayOfWeek.layoutParams = params

        binding.rvFragmentCalendar.adapter =
            RecyclerAdapter(
                arrayListOf(
                    Calendar(),
                    Calendar(),
                    Calendar(),
                    Calendar(),
                    Calendar(),
                    Calendar(),
                    Calendar()
                ),
                this,
                R.layout.layout_day_of_week,
            )

        binding.rvFragmentCalendar.layoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun onClick(t: Calendar) {
        Toast.makeText(requireContext(), "onClick", Toast.LENGTH_SHORT).show()
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        attachJobOfDayLayout(holder)
    }

    private fun attachJobOfDayLayout(holder: RecyclerAdapter<Calendar>.ViewHolder) {
        val rvJob: RecyclerView = holder.itemView.findViewById(R.id.rvJob)

        rvJob.adapter =
            RecyclerAdapter(
                arrayListOf(
                    Calendar(),
                    Calendar(),
                    Calendar()
                ),
                ActionInJobOfDay(holder, requireContext()),
                R.layout.layout_job_of_day,
            )

        rvJob.layoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL,
            false
        )
    }
}