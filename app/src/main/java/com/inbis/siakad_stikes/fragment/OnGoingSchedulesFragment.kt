package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.SpaceItemDecoration
import com.inbis.siakad_stikes.adapter.DayButtonAdapter
import com.inbis.siakad_stikes.adapter.OnGoingCourseAdapter
import com.inbis.siakad_stikes.databinding.FragmentOnGoingSchedulesBinding
import com.inbis.siakad_stikes.model.DayItemData
import com.inbis.siakad_stikes.model.OnGoingData

class OnGoingSchedulesFragment : Fragment() {

    private var _binding: FragmentOnGoingSchedulesBinding? = null
    private val binding get() = _binding!!

    private lateinit var dayAdapter: DayButtonAdapter
    private lateinit var courseAdapter: OnGoingCourseAdapter

    // List hari untuk filter
    private val days = listOf(
        DayItemData("Senin", true),
        DayItemData("Selasa"),
        DayItemData("Rabu"),
        DayItemData("Kamis"),
        DayItemData("Jumat"),
        DayItemData("Sabtu"),
        DayItemData("Minggu")
    )

    // Data mata kuliah
    private val allCourses = listOf(
        OnGoingData("Pemrograman Mobile", "Dr. Adi Nugroho", "Ruang Auditorium", "Senin", "08:00 - 10:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Selasa", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Rabu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Kamis", "15:00 - 17:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Jumat", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Sabtu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Sabtu", "15:00 - 17:00")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnGoingSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDaysRecyclerView()
        setupOnGoingCourseRecyclerView()

        // Tampilkan course Senin sebagai default
        updateCourses("Senin")
    }

    private fun setupDaysRecyclerView() {
        binding.btnDayRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.btnDayRecycler.addItemDecoration(SpaceItemDecoration(6))

        dayAdapter = DayButtonAdapter(days) { position ->
            val selectedDay = days[position].dayName
            days.forEachIndexed { index, day -> day.isSelected = index == position }
            dayAdapter.notifyDataSetChanged()
            updateCourses(selectedDay)
        }
        binding.btnDayRecycler.adapter = dayAdapter
    }

    private fun setupOnGoingCourseRecyclerView() {
        courseAdapter = OnGoingCourseAdapter(emptyList()) // Awalnya kosong
        binding.jadwalOngoingRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.jadwalOngoingRecycler.adapter = courseAdapter
    }

    private fun updateCourses(selectedDay: String) {
        val filteredCourses = allCourses.filter { it.courseDay == selectedDay }
        courseAdapter.updateData(filteredCourses)

        if (filteredCourses.isEmpty()) {
            binding.emptyCourseMessage.visibility = View.VISIBLE
            binding.jadwalOngoingRecycler.visibility = View.GONE
        } else {
            binding.emptyCourseMessage.visibility = View.GONE
            binding.jadwalOngoingRecycler.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
