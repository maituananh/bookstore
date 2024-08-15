package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentCalendarOfWeekBinding
import com.example.bookstore.fragment.action.ActionOnCalendarOfWeek
import com.example.bookstore.view_model.CalendarOfWeekViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarOfWeekFragment : Fragment(R.layout.fragment_calendar_of_week) {

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
        calendarViewModel.loading.observe(viewLifecycleOwner) {
            it?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        calendarViewModel.fetchCalendar()

        calendarViewModel.calendarList.observe(viewLifecycleOwner) {
            binding.rvFragmentCalendar.adapter =
                RecyclerAdapter(
                    it,
                    ActionOnCalendarOfWeek(requireContext(), calendarViewModel),
                    R.layout.layout_day_of_week
                )
        }

        binding.rvFragmentCalendar.layoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL,
            false
        )
    }
}