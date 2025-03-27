package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.data.OnGoingData
import com.inbis.siakad_stikes.databinding.ItemUpcomingSchedulesDashBinding

class DashSchedulesAdapter(private var dashCourseList: List<OnGoingData>) : RecyclerView.Adapter<DashSchedulesAdapter.DashSchedulesViewHolder>() {
    inner class DashSchedulesViewHolder(private val binding: ItemUpcomingSchedulesDashBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: OnGoingData) {
            binding.dashJadwalCourseNameCard.text = course.courseName
            binding.dashJadwalCourseHourCard.text = course.courseHour
            binding.dashJadwalCourseRoomCard.text = course.courseRoom
            binding.dashJadwalCourseLectureCard.text = course.courseLecture
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashSchedulesAdapter.DashSchedulesViewHolder {
        val binding = ItemUpcomingSchedulesDashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashSchedulesViewHolder((binding))
    }

    override fun onBindViewHolder(
        holder: DashSchedulesAdapter.DashSchedulesViewHolder,
        position: Int
    ) {
        holder.bind(dashCourseList[position])
    }

    override fun getItemCount(): Int {
        return dashCourseList.size
    }
}