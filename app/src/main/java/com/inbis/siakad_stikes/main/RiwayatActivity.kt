package com.inbis.siakad_stikes.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.databinding.ActivityRiwayatBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import com.inbis.siakad_stikes.Adapter.HistoryAdapter
import com.inbis.siakad_stikes.Model.HistoryItem

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

        // Dummy Data untuk RecyclerView
        val historyDummyList = listOf(
            HistoryItem("Pertemuan 1", "01 Jan 2024", "Hadir"),
            HistoryItem("Pertemuan 2", "08 Jan 2024", "Tidak Hadir"),
            HistoryItem("Pertemuan 3", "15 Jan 2024", "Sakit"),
            HistoryItem("Pertemuan 4", "22 Jan 2024", "Hadir"),
            HistoryItem("Pertemuan 5", "29 Jan 2024", "Hadir")
        )

        // Set Adapter ke RecyclerView
        binding.recyclerHistory.layoutManager = LinearLayoutManager(this)
        binding.recyclerHistory.adapter = HistoryAdapter(historyDummyList) { item ->
            showReportDialog(item) // Fungsi untuk menangani klik tombol report
        }

        // Spinner Mata Kuliah
        val mataKuliahList = listOf("Pilih Mata Kuliah", "Pemrograman Mobile", "Struktur Data", "Basis Data", "Jaringan Komputer")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mataKuliahList)
        binding.spinnerMatkul.adapter = adapter
    }

    private fun showReportDialog(item: HistoryItem) {
        AlertDialog.Builder(this)
            .setTitle("Laporkan Kehadiran")
            .setMessage("Apakah Anda ingin melaporkan status '${item.status}' pada ${item.pertemuan}?")
            .setPositiveButton("Ya") { _, _ ->
                Toast.makeText(this, "Laporan dikirim untuk ${item.pertemuan}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

}
