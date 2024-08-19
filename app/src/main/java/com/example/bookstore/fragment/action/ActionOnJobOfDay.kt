package com.example.bookstore.fragment.action

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.bookstore.R
import com.example.bookstore.action.IRecyclerAction
import com.example.bookstore.adapter.RecyclerAdapter
import com.example.bookstore.common.util.colorList
import com.example.bookstore.view_model.CalendarOfWeekViewModel
import com.example.domain.model.calendar.Job
import com.example.domain.model.calendar.JobStatus
import java.util.Locale


class ActionOnJobOfDay
    (
    private val context: Context,
    private val calendarViewModel: CalendarOfWeekViewModel
) : IRecyclerAction<Job> {

    private lateinit var imgIconNotCompleted: ImageView
    private lateinit var imgIconCompleted: ImageView
    private lateinit var txtExercise: TextView
    private lateinit var txtStatus: TextView
    private lateinit var cvJob: CardView
    private lateinit var ivDot: ImageView

    private lateinit var txtJobName: TextView

    override fun onClick(t: Job) {
        TODO("Not yet implemented")
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Job>.ViewHolder,
        position: Int,
        data: List<Job>
    ) {
        cvJob = holder.itemView.findViewById(R.id.cv_job)

        imgIconNotCompleted = holder.itemView.findViewById(R.id.img_not_completed_icon)
        imgIconNotCompleted.setOnClickListener(onClickNotCompleteJob(data[position]))

        imgIconCompleted = holder.itemView.findViewById(R.id.img_completed_icon)
        imgIconCompleted.setOnClickListener(onClickCompleteJob(data[position]))

        txtExercise = holder.itemView.findViewById(R.id.txt_exercise_number)
        txtStatus = holder.itemView.findViewById(R.id.txt_status)

        ivDot = holder.itemView.findViewById(R.id.iv_dot)

        txtJobName = holder.itemView.findViewById(R.id.txt_job_name)

        setDataInJob(position, data)
        setIconCompleted(data[position])
        setColorInStatus(data[position])
    }

    private fun setColorInStatus(job: Job) {
        when (job.status) {
            JobStatus.MISSED -> {
                cvJob.setCardBackgroundColor(context.colorList(R.color.white))
                txtStatus.setTextColor(context.colorList(R.color.red))
            }

            JobStatus.COMPLETED -> {
                cvJob.setCardBackgroundColor(context.colorList(R.color.purple))
                setColorForTextJob(R.color.white)

                txtExercise.isVisible = false
                ivDot.isVisible = false

                imgIconCompleted.apply {
                    setImageResource(R.drawable.completed_icon)
                    setOnClickListener(null)
                    isClickable = false
                }

                imgIconNotCompleted.apply {
                    setOnClickListener(null)
                    isClickable = false
                }
            }

            else -> setColorForTextJob(R.color.silver)
        }
    }

    private fun setDataInJob(
        position: Int,
        jobs: List<Job>
    ) {

        txtJobName.text = jobs[position].name

        val status = jobs[position].status
        if (status != JobStatus.ASSIGNED) {
            val firstCharStatus = status.name.substring(0, 1)
            val secondCharsStatus =
                status.name.substring(1, status.name.length).lowercase(Locale.ROOT)

            txtStatus.text =
                StringBuilder().append(firstCharStatus).append(secondCharsStatus).toString()
        } else {
            ivDot.isVisible = false
        }

        when (val numberOfExercise = jobs[position].exercises) {
            0 -> txtExercise.text = ""
            1 -> txtExercise.text =
                context.getString(R.string.exercise, numberOfExercise.toString())

            else -> txtExercise.text =
                context.getString(R.string.exercises, numberOfExercise.toString())
        }
    }

    private fun onClickNotCompleteJob(
        job: Job
    ): View.OnClickListener {
        return View.OnClickListener {
            imgIconCompleted.setImageResource(R.drawable.completed_icon_purple)
            it.visibility = View.GONE
            imgIconCompleted.visibility = View.VISIBLE

            job.isSelected = true
            calendarViewModel.updateIsSelected(job)
        }
    }

    private fun onClickCompleteJob(
        job: Job
    ): View.OnClickListener {
        return View.OnClickListener {
            imgIconNotCompleted.setImageResource(R.drawable.completed_icon)
            it.visibility = View.GONE
            imgIconNotCompleted.visibility = View.VISIBLE

            job.isSelected = false
            calendarViewModel.updateIsSelected(job)
        }
    }

    private fun setIconCompleted(job: Job) {
        if (job.isSelected) {
            imgIconCompleted.visibility = View.VISIBLE
        } else {
            imgIconNotCompleted.visibility = View.VISIBLE
        }
    }

    private fun setColorForTextJob(@ColorRes color: Int) {
        txtJobName.setTextColor(context.colorList(color))
        txtStatus.setTextColor(context.colorList(color))
        txtExercise.setTextColor(context.colorList(color))
    }
}