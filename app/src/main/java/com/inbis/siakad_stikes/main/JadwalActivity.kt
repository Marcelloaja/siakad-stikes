package com.inbis.siakad_stikes.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityJadwalBinding
import com.inbis.siakad_stikes.fragment.CompletedSchedulesFragment
import com.inbis.siakad_stikes.fragment.OnGoingSchedulesFragment

class JadwalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        showCourseSchedule()
        showCourseInformation(OnGoingSchedulesFragment())
        actionButton()

    }

    private fun actionButton() {
        binding.btnBackJadwal.setOnClickListener {
            finish()
        }
    }

    private fun showCourseSchedule() {
        binding.btnJadwalOngoing.setOnClickListener {
            showCourseInformation(OnGoingSchedulesFragment())
            choiceCourse(binding.btnJadwalOngoing)
        }

        binding.btnJadwalCompleted.setOnClickListener {
            showCourseInformation(CompletedSchedulesFragment())
            choiceCourse(binding.btnJadwalCompleted)
        }
    }

    private fun showCourseInformation(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_jadwal_container, fragment)
        fragmentTransaction.commit()
    }

    private fun choiceCourse(choicesButton : MaterialButton) {
        val button = listOf(binding.btnJadwalOngoing, binding.btnJadwalCompleted)

        button.forEach { button ->
            if (button == choicesButton) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                button.setTextColor(ContextCompat.getColor(this, R.color.black))
            } else {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.new_grey2))
                button.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
    }


}