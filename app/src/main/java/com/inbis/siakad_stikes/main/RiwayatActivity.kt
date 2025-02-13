package com.inbis.siakad_stikes.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.inbis.siakad_stikes.databinding.ActivityRiwayatBinding
import android.widget.ArrayAdapter

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.btnBackHistory.setOnClickListener {
            finish()
        }

        val mataKuliahList = listOf("Pilih Mata Kuliah", "Pemrograman Mobile", "Struktur Data", "Basis Data", "Jaringan Komputer")

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mataKuliahList)
        binding.spinnerMatkul.adapter = adapter
    }

}