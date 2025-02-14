package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.databinding.ListCourseOngoingBinding
import com.inbis.siakad_stikes.model.OnGoingData

class OnGoingCourseAdapter(private val courseList: List<OnGoingData>) : RecyclerView.Adapter<OnGoingCourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnGoingCourseAdapter.CourseViewHolder {
        val binding = ListCourseOngoingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    inner class CourseViewHolder(private val binding: ListCourseOngoingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: OnGoingData) {
            binding.jadwalCourseNameCard.text = course.courseName
            binding.jadwalCourseHourCard.text = course.courseHour
            binding.jadwalCourseLectureCard.text = course.courseLecture
            binding.jadwalCourseRoomCard.text = course.courseRoom
        }
    }

    override fun onBindViewHolder(holder: OnGoingCourseAdapter.CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}