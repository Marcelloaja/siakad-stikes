package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.databinding.ListCourseCompletedBinding
import com.inbis.siakad_stikes.model.CompletedData

class CompletedCourseAdapter(private var courseComList: List<CompletedData>) : RecyclerView.Adapter<CompletedCourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletedCourseAdapter.CourseViewHolder {
        val binding = ListCourseCompletedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    inner class CourseViewHolder(private val binding: ListCourseCompletedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CompletedData) {
            binding.jadwalCourseCompletedName.text = course.courseComName
            binding.jadwalCourseCompletedCode.text = course.courseComKode
            binding.jadwalCourseCompletedSifat.text = course.courseComSifat
            binding.jadwalCourseCompletedSks.text = course.courseComSks
            binding.jadwalCourseCompletedNilai.text = course.courseComNilai
        }

    }

    override fun onBindViewHolder(holder: CompletedCourseAdapter.CourseViewHolder, position: Int) {
        holder.bind(courseComList[position])
    }

    override fun getItemCount(): Int {
        return courseComList.size
    }
}