package com.inbis.siakad_stikes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.adapter.HourButtonAdapter
import com.inbis.siakad_stikes.adapter.OnGoingCourseAdapter
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.main.JadwalActivity
import com.inbis.siakad_stikes.model.HourItemData
import com.inbis.siakad_stikes.model.OnGoingData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hourAdapter: HourButtonAdapter
    private lateinit var courseAdapter: OnGoingCourseAdapter

    private val hours = listOf(
        HourItemData("08:00", true),
        HourItemData("09:00"),
        HourItemData("10:00"),
        HourItemData("11:00"),
        HourItemData("12:00"),
        HourItemData("13:00"),
        HourItemData("14:00"),
        HourItemData("15:00"),
        HourItemData("16:00")
    )

    private val allCourses = listOf(
        OnGoingData("Pemrograman Mobile", "Dr. Adi Nugroho", "Ruang Auditorium", "Senin", "08:00 - 10:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Selasa", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Rabu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Kamis", "15:00 - 17:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Jumat", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Sabtu", "13:00 - 15:00"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Sabtu", "15:00 - 17:00")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setupHoursRecyclerView()
        setupOnGoingCourseRecyclerView()
        updateCourses("08:00")

        binding.btnNotification.setOnClickListener {
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupHoursRecyclerView() {
        binding.btnHoursRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        hourAdapter = HourButtonAdapter(hours) { position ->
            val selectedHour = hours[position].hourName
            hours.forEachIndexed { index, hour -> hour.isSelected = index == position }
            hourAdapter.notifyDataSetChanged()
            updateCourses(selectedHour)
        }
        binding.btnHoursRecycler.adapter = hourAdapter
    }

    private fun setupOnGoingCourseRecyclerView() {
        binding.jadwalOngoingRecycler.layoutManager = LinearLayoutManager(this)
        courseAdapter = OnGoingCourseAdapter(emptyList())
        binding.jadwalOngoingRecycler.adapter = courseAdapter
    }

    private fun updateCourses(selectedHour: String) {
        val filteredCourses = allCourses.filter { it.courseHour.startsWith(selectedHour) }
        Log.d("DEBUG", "Jam yang dipilih: $selectedHour, Course yang ditemukan: ${filteredCourses.size}")

        courseAdapter.updateData(filteredCourses)
    }

//    private fun actionButton() {
//        binding.btnScan.setOnClickListener {
//            val btnScan = binding.btnScan
//            val targetIntent = ScanActivity::class.java
//            animateButton(btnScan, this, targetIntent)
//        }
//
//        binding.btnProfile.setOnClickListener {
//            val btnProf = binding.btnProfile
//            val targetIntent = ProfileActivity::class.java
//            animateButton(btnProf, this, targetIntent)
//        }
//
//        binding.btnSchedule.setOnClickListener {
//            val btnSchedule = binding.btnSchedule
//            val targetIntent = JadwalActivity::class.java
//            animateButton(btnSchedule, this, targetIntent)
//        }
//
//        binding.btnHistory.setOnClickListener {
//            val btnHistory = binding.btnHistory
//            val targetIntent = RiwayatActivity::class.java
//            animateButton(btnHistory, this, targetIntent)
//        }
//
//        binding.btnNotification.setOnClickListener {
//            val btnNotif = binding.btnNotification
//            val targetIntent = NotificationActivity::class.java
//            animateButton(btnNotif, this, targetIntent)
//        }
//    }
}
