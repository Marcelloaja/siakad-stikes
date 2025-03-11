package com.inbis.siakad_stikes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.inbis.siakad_stikes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
//        actionButton()
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