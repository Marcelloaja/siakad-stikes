package com.inbis.siakad_stikes.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.adapter.HistoryAdapter
import com.inbis.siakad_stikes.data.HistoryItem
import com.inbis.siakad_stikes.databinding.FragmentRiwayatBinding


class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!


    private val dummyListHistory = listOf(
        HistoryItem("Pertemuan 1", "01 Jan 2024", "Hadir"),
        HistoryItem("Pertemuan 2", "08 Jan 2024", "Tidak Hadir"),
        HistoryItem("Pertemuan 3", "15 Jan 2024", "Sakit"),
        HistoryItem("Pertemuan 4", "22 Jan 2024", "Hadir"),
        HistoryItem("Pertemuan 5", "29 Jan 2024", "Hadir")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRiwayatRecyclerView()

        val listMataKuliah = listOf(
            "Pilih Mata Kuliah",
            "Pemrograman Mobile",
            "Struktur Data",
            "Basis Data",
            "Jaringan Komputer"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listMataKuliah
        )
        binding.frSpinnerMatkul.adapter = adapter
    }

    private fun setupRiwayatRecyclerView() {
        binding.frRecyclerHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.frRecyclerHistory.adapter = HistoryAdapter(dummyListHistory) { item ->
            showReportDialog(item)
        }
    }

    private fun showReportDialog(item: HistoryItem) {
        AlertDialog.Builder(requireContext())
            .setTitle("Laporkan Kehadiran")
            .setMessage("Apakah Anda ingin melaporkan status '${item.status}' pada ${item.pertemuan}?")
            .setPositiveButton("Ya") { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "Laporan dikirim untuk ${item.pertemuan}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}