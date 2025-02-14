package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.adapter.CompletedCourseAdapter
import com.inbis.siakad_stikes.adapter.OnGoingCourseAdapter
import com.inbis.siakad_stikes.databinding.FragmentCompletedSchedulesBinding
import com.inbis.siakad_stikes.model.CompletedData
import com.inbis.siakad_stikes.model.OnGoingData


class CompletedSchedulesFragment : Fragment() {

    private var _binding: FragmentCompletedSchedulesBinding? = null
    private val binding get() = _binding!!

//    private lateinit var adapter: CompletedCourseAdapter
    private lateinit var dummyCompletedCourse: List<CompletedData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCourseAll.setOnClickListener { updateSchedulesAction(binding.btnCourseAll) }
        binding.btnCourseMandatory.setOnClickListener { updateSchedulesAction(binding.btnCourseMandatory) }
        binding.btnCourseElective.setOnClickListener { updateSchedulesAction(binding.btnCourseElective) }

        updateSchedulesAction(binding.btnCourseAll)

       dummyCompletedCourse = listOf(
            CompletedData("Teknik Supranatural", "M11K90", "Wajib", "3", "B+"),
            CompletedData("Mesin Pengembang", "K118J28", "Pilihan", "2", "A"),
            CompletedData("Penanaman Modal", "B002L11", "Pilihan", "2", "B"),
            CompletedData("Pengolahan Pangan", "M922K38", "Wajib", "3", "A"),
            CompletedData("Jaringan Saraf", "M291L98", "Wajib", "2", "A"),
            CompletedData("Jaminan Mutu", "K911K90", "Pilihan", "3", "B+"),
            CompletedData("UUD", "M923L88", "Wajib", "4", "A"),
            CompletedData("Penawaran Masuk Akal", "B002K93", "Wajib", "3", "A"),
            CompletedData("Bursa Second", "L834K77", "Pilihan", "4", "B+"),
            CompletedData("Keamanan Siber", "C210M19", "Wajib", "3", "A"),
            CompletedData("Analisis Big Data", "D331K22", "Pilihan", "2", "B"),
            CompletedData("Pemrograman Berbasis Cloud", "M442L55", "Wajib", "3", "A-"),
            CompletedData("Robotika Dasar", "R509J87", "Pilihan", "2", "B+"),
            CompletedData("Interaksi Manusia dan Komputer", "H671L23", "Wajib", "3", "A"),
            CompletedData("Pemodelan 3D", "T782M45", "Pilihan", "2", "B"),
            CompletedData("Etika Profesi", "E893L67", "Wajib", "3", "A-"),
            CompletedData("Manajemen Proyek IT", "P902K11", "Pilihan", "2", "B+"),
            CompletedData("Keamanan Jaringan", "S134M22", "Wajib", "3", "A"),
            CompletedData("Sistem Cerdas", "X214L56", "Pilihan", "2", "B"),
            CompletedData("Teknik Simulasi", "Y371K98", "Wajib", "3", "A"),
            CompletedData("Blockchain dan Kriptografi", "B841M33", "Pilihan", "2", "A-"),
            CompletedData("Pengantar Kecerdasan Buatan", "AI301L76", "Wajib", "3", "A"),
            CompletedData("Pengolahan Citra Digital", "PC123L89", "Pilihan", "2", "B+"),
            CompletedData("Internet of Things (IoT)", "IOT456M92", "Wajib", "3", "A"),
            CompletedData("Statistika Terapan", "ST221L64", "Pilihan", "2", "B"),
            CompletedData("Metodologi Penelitian", "MP889K55", "Wajib", "3", "A"),
            CompletedData("Pengembangan Game", "PG661M77", "Pilihan", "2", "B+")
        )

        val dummyOnGoingCourse = listOf(
            OnGoingData("Pemrograman Mobile", "Dr. Adi Nugroho", "R101", "08:00 - 10:00"),
            OnGoingData("Basis Data", "Dr. Rina Sari", "R102", "10:00 - 12:00"),
            OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "R103", "13:00 - 15:00"),
            OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "R104", "15:00 - 17:00"),
            OnGoingData("Basis Data", "Dr. Rina Sari", "R102", "10:00 - 12:00"),
            OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "R103", "13:00 - 15:00"),
            OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "R104", "15:00 - 17:00")
        )

        val adapter = OnGoingCourseAdapter(dummyOnGoingCourse)
        binding.jadwalCompletedRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

    }

    private fun updateSchedulesAction(selectedButton: MaterialButton) {
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}