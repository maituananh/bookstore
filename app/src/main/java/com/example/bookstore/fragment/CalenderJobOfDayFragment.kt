package com.example.bookstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.databinding.FragmentCalenderJobOfDayBinding
import com.example.domain.model.calendar.Calendar

class CalenderJobOfDayFragment : Fragment(R.layout.fragment_calender_job_of_day),
    IRecyclerAction<Calendar> {

    private lateinit var binding: FragmentCalenderJobOfDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalenderJobOfDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvFragmentCalendar.adapter =
            RecyclerAdapter(
                arrayListOf(
                    Calendar(),
                    Calendar()
                ),
                this,
                R.layout.layout_job_of_day,
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
        Toast.makeText(requireContext(), "Job of day", Toast.LENGTH_SHORT).show()
    }
}