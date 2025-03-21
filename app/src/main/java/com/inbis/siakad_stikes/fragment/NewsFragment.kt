package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.adapter.NewsAdapter
import com.inbis.siakad_stikes.data.NewsData
import com.inbis.siakad_stikes.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    private val newsAll = listOf(
        NewsData("Dr. Sutomo", "Pencairan uang asisten dosen 2024", "03 Januari, 2025"),
        NewsData(
            "Prof. Hidayat",
            "Pendaftaran Beasiswa Kampus Merdeka Dibuka!",
            "10 Januari, 2025"
        ),
        NewsData("Dr. Rahmawati", "Jadwal Ujian Akhir Semester Genap 2025", "15 Januari, 2025"),
        NewsData(
            "Dr. Indra Wijaya",
            "Seminar Nasional Kesehatan dan Teknologi",
            "20 Januari, 2025"
        ),
        NewsData("Biro Akademik", "Pengambilan Kartu Ujian Mulai Minggu Depan", "25 Januari, 2025"),
        NewsData(
            "Rektorat",
            "Wisuda Periode Maret 2025 Akan Dilaksanakan Offline",
            "01 Februari, 2025"
        ),
        NewsData(
            "Dekan Fakultas Kedokteran",
            "Praktikum Laboratorium Akan Menggunakan Teknologi AR",
            "05 Februari, 2025"
        ),
        NewsData(
            "Dr. Surya",
            "Pendaftaran KKN Tematik 2025 Dibuka Hingga Akhir Bulan",
            "10 Februari, 2025"
        ),
        NewsData(
            "BEM Kampus",
            "Diskusi Publik: Tantangan Pendidikan di Era Digital",
            "15 Februari, 2025"
        ),
        NewsData(
            "Prof. Amin",
            "Workshop Penelitian dan Publikasi Jurnal Internasional",
            "20 Februari, 2025"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNewsRecyclerView()
    }

    private fun setupNewsRecyclerView() {
        newsAdapter = NewsAdapter(newsAll)
        binding.newsPageRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}