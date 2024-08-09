package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentCalendarOfWeekBinding
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

    override fun onClick(book: Calendar) {
        Toast.makeText(requireContext(), "onClick", Toast.LENGTH_SHORT).show()
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        this.getChildFragmentManager()
            .beginTransaction()
            .replace(
                holder.itemView.findViewById<FrameLayout>(R.id.replace_by_fragment_job_of_day).id,
                CalenderJobOfDayFragment()
            )
            .commit()
        Toast.makeText(requireContext(), "Day of week", Toast.LENGTH_SHORT).show()
    }

}