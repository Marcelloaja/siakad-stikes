package com.inbis.siakad_stikes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.databinding.ListResumeAttendanceBinding
import com.inbis.siakad_stikes.model.ResumeData

class ResumeAdapter(private var resumeList: List<ResumeData>) :
    RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResumeAdapter.ResumeViewHolder {
        val binding =
            ListResumeAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResumeViewHolder(binding)
    }

    inner class ResumeViewHolder(private val binding: ListResumeAttendanceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(resume: ResumeData) {
            binding.resumeLessonName.text = resume.resumeLesson
            binding.resumeLectureName.text = resume.resumeLecture
            binding.resumeAttendResult.text = resume.resumeAttend.toString()
            binding.resumeSickResult.text = resume.resumeSick.toString()
            binding.resumeAlphaResult.text = resume.resumeAlpha.toString()
            binding.resumeTotalResult.text = resume.resumeTotal.toString()
        }
    }

    override fun onBindViewHolder(holder: ResumeAdapter.ResumeViewHolder, position: Int) {
        holder.bind(resumeList[position])
    }

    override fun getItemCount(): Int {
        return resumeList.size
    }
}