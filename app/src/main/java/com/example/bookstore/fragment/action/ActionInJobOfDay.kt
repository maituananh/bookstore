package com.example.bookstore.fragment.action

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job


class ActionInJobOfDay(
//    private val parentHolder: RecyclerAdapter<Calendar>.ViewHolder,
    private val context: Context
) : IRecyclerAction<Job> {
    override fun onClick(t: Job) {
        println("ActionInJobOfDay")
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Job>.ViewHolder,
        position: Int,
        data: List<Job>
    ) {
        val txtStatus = holder.itemView.findViewById<TextView>(R.id.txt_status)
        val txtExerciseNumber = holder.itemView.findViewById<TextView>(R.id.txt_exercise_number)
        val txtJobName = holder.itemView.findViewById<TextView>(R.id.txt_job_name)

        txtStatus.text =
            data[position].status.toString().substring(0, 1) + data[position].status.toString()
                .substring(1, data[position].status.toString().length).toLowerCase()
        txtJobName.text = data[position].name

        if (data.size == 1) {
            txtExerciseNumber.text = data[position].exercises.toString() + " exercise"
        } else if (data.size == 0) {
            txtExerciseNumber.text = ""
        } else {
            txtExerciseNumber.text = data[position].exercises.toString() + " exercises"
        }

        val imgTickCompleted = holder.itemView.findViewById<ImageView>(R.id.img_tick_completed)
        imgTickCompleted.setOnClickListener(onClickCompleteJob(holder, imgTickCompleted))
    }

    @SuppressLint("ResourceAsColor")
    private fun onClickCompleteJob(
        holder: RecyclerAdapter<Job>.ViewHolder,
        imgView: ImageView
    ): View.OnClickListener {
        return View.OnClickListener {
            imgView.setImageResource(R.drawable.completed_icon_purple)
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