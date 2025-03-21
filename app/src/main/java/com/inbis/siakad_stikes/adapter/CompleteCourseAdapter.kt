package com.inbis.siakad_stikes.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.databinding.ListCourseCompletedBinding
import com.inbis.siakad_stikes.data.CompleteData

class CompleteCourseAdapter(private var comCourse: MutableList<CompleteData>) :
    RecyclerView.Adapter<CompleteCourseAdapter.ComViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompleteCourseAdapter.ComViewHolder {
        val binding =
            ListCourseCompletedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComViewHolder(binding)
    }

    inner class ComViewHolder(private val binding: ListCourseCompletedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(courseCome: CompleteData) {
            binding.jadwalCourseCompletedName.text = courseCome.comCourseName
            binding.jadwalCourseCompletedCode.text = courseCome.comCourseCode
            binding.jadwalCourseCompletedSks.text = courseCome.comCourseSks
            binding.jadwalCourseCompletedSifat.text = courseCome.comCourseSifat
            binding.jadwalCourseCompletedNilai.text = courseCome.comCourseValue
        }

    }

    override fun onBindViewHolder(holder: CompleteCourseAdapter.ComViewHolder, position: Int) {
        holder.bind(comCourse[position])
    }

    override fun getItemCount(): Int {
        return comCourse.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<CompleteData>) {
        Log.d("DEBUG", "Adapter received data count: ${newList.size}")
        comCourse.clear()
        comCourse.addAll(newList)
        notifyDataSetChanged()
    }
}