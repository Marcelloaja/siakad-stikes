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
import com.inbis.siakad_stikes.data.DayItemData
import com.inbis.siakad_stikes.data.OnGoingData

class OnGoingSchedulesFragment : Fragment() {

    private var _binding: FragmentOnGoingSchedulesBinding? = null
    private val binding get() = _binding!!

    private lateinit var dayAdapter: DayButtonAdapter
    private lateinit var courseAdapter: OnGoingCourseAdapter

    private val days = listOf(
        DayItemData("Senin", true),
        DayItemData("Selasa"),
        DayItemData("Rabu"),
        DayItemData("Kamis"),
        DayItemData("Jumat"),
        DayItemData("Sabtu"),
        DayItemData("Minggu")
    )

    private val allCourses = listOf(
        OnGoingData("Pemrograman Mobile Pemrograman Mobile Pemrograman Mobile", "Dr. Adi Nugroho", "Ruang Auditorium", "Senin", "08:00 - 10:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Selasa", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Rabu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Kamis", "15:00 - 17:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Jumat", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Sabtu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Sabtu", "15:00 - 17:00"),
        OnGoingData("Algoritma dan Struktur Data", "Dr. Ahmad Fauzi", "Ruang 101", "Senin", "07:00 - 09:00"),
        OnGoingData("Sistem Operasi", "Dr. Dian Pratama", "Ruang 102", "Selasa", "09:00 - 11:00"),
        OnGoingData("Pemrograman Web", "Dr. Eka Wijaya", "Ruang 103", "Rabu", "12:00 - 14:00"),
        OnGoingData("Machine Learning", "Dr. Fitriani", "Ruang 104", "Kamis", "14:00 - 16:00"),
        OnGoingData("Keamanan Jaringan", "Dr. Gita Putri", "Ruang 105", "Jumat", "11:00 - 13:00"),
        OnGoingData("Pengolahan Citra Digital", "Dr. Hadi Santoso", "Ruang 106", "Sabtu", "10:00 - 12:00"),
        OnGoingData("Internet of Things", "Dr. Indra Wijaya", "Ruang 107", "Minggu", "13:00 - 15:00"),
        OnGoingData("Rekayasa Perangkat Lunak", "Dr. Joko Susilo", "Ruang 201", "Senin", "08:30 - 10:30"),
        OnGoingData("Basis Data Lanjut", "Dr. Kartika Dewi", "Ruang 202", "Selasa", "10:30 - 12:30"),
        OnGoingData("Jaringan Nirkabel", "Dr. Lina Hartati", "Ruang 203", "Rabu", "13:30 - 15:30"),
        OnGoingData("Deep Learning", "Dr. Mulyadi", "Ruang 204", "Kamis", "15:30 - 17:30"),
        OnGoingData("Sistem Cerdas", "Dr. Nia Kurnia", "Ruang 205", "Jumat", "09:30 - 11:30"),
        OnGoingData("Analisis Big Data", "Dr. Oki Pratama", "Ruang 206", "Sabtu", "14:30 - 16:30"),
        OnGoingData("Cloud Computing", "Dr. Putra Wijaya", "Ruang 207", "Minggu", "16:30 - 18:30"),
        OnGoingData("Pemrograman Berorientasi Objek", "Dr. Rudi Hartono", "Ruang 301", "Senin", "07:30 - 09:30"),
        OnGoingData("Manajemen Proyek TI", "Dr. Sari Dewi", "Ruang 302", "Selasa", "09:30 - 11:30"),
        OnGoingData("Komputasi Paralel", "Dr. Taufik Hidayat", "Ruang 303", "Rabu", "12:30 - 14:30"),
        OnGoingData("Data Mining", "Dr. Umar Said", "Ruang 304", "Kamis", "14:30 - 16:30"),
        OnGoingData("Keamanan Siber", "Dr. Vera Wijaya", "Ruang 305", "Jumat", "10:30 - 12:30"),
        OnGoingData("Pengembangan Aplikasi Mobile", "Dr. Widi Astuti", "Ruang 306", "Sabtu", "13:30 - 15:30"),
        OnGoingData("Blockchain", "Dr. Yudi Pratama", "Ruang 307", "Minggu", "15:30 - 17:30")
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
