package com.inbis.siakad_stikes.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityJadwalBinding
import com.inbis.siakad_stikes.fragment.OnGoingSchedulesFragment
import com.inbis.siakad_stikes.fragment.ProfileFragment
import com.inbis.siakad_stikes.fragment.ResumeAttendanceFragment

class JadwalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        showCourseInformation(ResumeAttendanceFragment())
        actionButton()

    }

    private fun actionButton() {
        binding.btnBackJadwal.setOnClickListener {
            finish()
        }
    }

    private fun showCourseInformation(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_jadwal_container, fragment)
        fragmentTransaction.commit()
    }
}