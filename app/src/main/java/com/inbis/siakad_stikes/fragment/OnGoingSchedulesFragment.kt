package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.adapter.DayButtonAdapter
import com.inbis.siakad_stikes.adapter.OnGoingCourseAdapter
import com.inbis.siakad_stikes.databinding.FragmentOnGoingSchedulesBinding
import com.inbis.siakad_stikes.model.DayItemData
import com.inbis.siakad_stikes.model.OnGoingData


class OnGoingSchedulesFragment : Fragment() {

    private var _binding: FragmentOnGoingSchedulesBinding? = null
    private val binding get() = _binding!!

    private lateinit var dayAdapter: DayButtonAdapter
    private val days = listOf(
        DayItemData("Senin", true),
        DayItemData("Selasa"),
        DayItemData("Rabu"),
        DayItemData("Kamis"),
        DayItemData("Jumat"),
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


    }

    private fun setupDaysRecyclerView() {
        binding.btnDayRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        dayAdapter = DayButtonAdapter(days) { position ->
            days.forEachIndexed { index, day ->
                day.isSelected = index == position
            }
            dayAdapter.notifyDataSetChanged()
        }
        binding.btnDayRecycler.adapter = dayAdapter
    }

    private fun setupOnGoingCourseRecyclerView() {
        val dummyOnGoingCourse = listOf(
            OnGoingData("Pemrograman Mobile", "Dr. Adi Nugroho", "R101", "08:00 - 10:00"),
            OnGoingData("Basis Data", "Dr. Rina Sari", "R102", "10:00 - 12:00"),
            OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "R103", "13:00 - 15:00"),
            OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "R104", "15:00 - 17:00"),
            OnGoingData(    "Basis Data", "Dr. Rina Sari", "R102", "10:00 - 12:00"),
            OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "R103", "13:00 - 15:00"),
            OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "R104", "15:00 - 17:00")
        )

        val adapter = OnGoingCourseAdapter(dummyOnGoingCourse)
        binding.jadwalOngoingRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}