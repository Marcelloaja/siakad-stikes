package com.inbis.siakad_stikes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.main.JadwalActivity
import com.inbis.siakad_stikes.main.ProfileActivity
import com.inbis.siakad_stikes.main.RiwayatActivity
import com.inbis.siakad_stikes.main.ScanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionButton()

    }

    private fun actionButton() {
        binding.btnScan.setOnClickListener {
            val intentScan = Intent(this, ScanActivity::class.java)
            startActivity(intentScan)
        }

        binding.btnProfile.setOnClickListener {
            val intentProfile = Intent(this, ProfileActivity::class.java)
            startActivity(intentProfile)
        }

        binding.btnSchedule.setOnClickListener {
            val intentSchedule = Intent(this, JadwalActivity::class.java)
            startActivity(intentSchedule)
        }

        binding.btnHistory.setOnClickListener {
            val intentHistory = Intent(this, RiwayatActivity::class.java)
            startActivity(intentHistory)
        }
    }
}