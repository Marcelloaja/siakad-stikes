package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.adapter.CompleteCourseAdapter
import com.inbis.siakad_stikes.databinding.FragmentCompletedSchedulesBinding
import com.inbis.siakad_stikes.data.CompleteData


class CompletedSchedulesFragment : Fragment() {

    private var _binding: FragmentCompletedSchedulesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CompleteCourseAdapter

    private val dummyCompletedCourse = listOf(
        CompleteData("Teknik Supranatural", "M11K90", "Wajib", "3", "B+"),
        CompleteData("Mesin Pengembang", "K118J28", "Pilihan", "2", "A"),
        CompleteData("Penanaman Modal", "B002L11", "Pilihan", "2", "B"),
        CompleteData("Pengolahan Pangan", "M922K38", "Wajib", "3", "A"),
        CompleteData("Jaringan Saraf", "M291L98", "Wajib", "2", "A"),
        CompleteData("Jaminan Mutu", "K911K90", "Pilihan", "3", "B+"),
        CompleteData("Teknik Silat", "M923L88", "Wajib", "4", "A"),
        CompleteData("Penawaran Masuk Akal", "B002K93", "Wajib", "3", "A"),
        CompleteData("Bursa Second", "L834K77", "Pilihan", "4", "B+"),
        CompleteData("Keamanan Siber", "C210M19", "Wajib", "3", "A"),
        CompleteData("Analisis Big Data", "D331K22", "Pilihan", "2", "B"),
        CompleteData("Pemrograman Berbasis Cloud", "M442L55", "Wajib", "3", "A-"),
        CompleteData("Robotika Dasar", "R509J87", "Pilihan", "2", "B+"),
        CompleteData("Interaksi Manusia dan Komputer", "H671L23", "Wajib", "3", "A"),
        CompleteData("Pemodelan 3D", "T782M45", "Pilihan", "2", "B"),
        CompleteData("Etika Profesi", "E893L67", "Wajib", "3", "A-"),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CompleteCourseAdapter(dummyCompletedCourse.toMutableList())
        binding.jadwalCompletedRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CompletedSchedulesFragment.adapter
        }

        binding.btnCourseAll.setOnClickListener { updateSchedulesAction(binding.btnCourseAll, "All") }
        binding.btnCourseMandatory.setOnClickListener { updateSchedulesAction(binding.btnCourseMandatory, "Wajib") }
        binding.btnCourseElective.setOnClickListener { updateSchedulesAction(binding.btnCourseElective, "Pilihan") }

        updateSchedulesAction(binding.btnCourseAll, "All")

    }

    private fun updateSchedulesAction(selectedButton: MaterialButton, filter: String) {
        val comButton = listOf(binding.btnCourseAll, binding.btnCourseMandatory, binding.btnCourseElective)

        comButton.forEach { button ->
            if (button == selectedButton) {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.new_orange))
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                button.strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.new_grey2)
            } else {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                button.strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.new_grey2)
            }
        }

        val filteredList = when (filter) {
            "Wajib" -> dummyCompletedCourse.filter { it.comCourseSifat == "Wajib" }
            "Pilihan" -> dummyCompletedCourse.filter { it.comCourseSifat == "Pilihan" }
            else -> dummyCompletedCourse
        }

        Log.d("DEBUG", "Filter: $filter, Filtered Size: ${filteredList.size}")

        adapter.updateData(filteredList)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}