package com.inbis.siakad_stikes.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.SpaceItemDecoration
import com.inbis.siakad_stikes.adapter.DashSchedulesAdapter
import com.inbis.siakad_stikes.adapter.NewsAdapter
import com.inbis.siakad_stikes.adapter.ResumeAdapter
import com.inbis.siakad_stikes.auth.IntroActivity
import com.inbis.siakad_stikes.auth.LoginActivity
import com.inbis.siakad_stikes.data.NewsData
import com.inbis.siakad_stikes.data.OnGoingData
import com.inbis.siakad_stikes.data.ResumeData
import com.inbis.siakad_stikes.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var resumeAdapter: ResumeAdapter
    private lateinit var dashSchedulesAdapter: DashSchedulesAdapter

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

    private val resumePageAll = listOf(
        ResumeData(
            "Pengadaan Tanggulangan Kesehatan Masyarakat",
            "Dr. Indra Wijaya, M.Ps",
            10,
            3,
            2,
            10
        ),
        ResumeData(
            "Intensifitas Kadar Gula Pada Harimau Malaya",
            "Suparto, S.Pt, M.U.I",
            8,
            1,
            1,
            8
        ),
        ResumeData("Penyaringan Infus Janin Dengan Selang", "Dr. Tirta M.Pd", 12, 1, 2, 12),
        ResumeData("Rekayasa Agama", "Dr. Ustadz Haji Salim", 11, 2, 2, 11),
        ResumeData("Pergantian Zaman dengan Metode Hijrah", "Dr. Freeze", 14, 1, 1, 14),
        ResumeData("Teknologi Peradaban Ghoib", "Marcello Ilham", 16, 0, 0, 16),
        ResumeData("Pencairan Es Batu : Teknik Adaptasi", "Kim Sun Chul, S.Pd, Alm", 16, 0, 0, 16),
        ResumeData("Sistem Pengembangan Bisnis Gelap", "Dr. Sukijat, M.MT", 16, 1, 1, 16),
    )

    private val allDashCourse = listOf(
        OnGoingData(
            "Pemrograman Mobile",
            "Dr. Adi Nugroho",
            "Ruang Auditorium",
            "Senin",
            "08:00 - 10:00"
        ),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Selasa", "10:00 - 12:00"),
        OnGoingData(
            "Jaringan Komputer",
            "Dr. Budi Santoso",
            "Ruang Auditorium",
            "Rabu",
            "13:00 - 15:00"
        ),
        OnGoingData(
            "Kecerdasan Buatan",
            "Dr. Siti Rahmah",
            "Ruang Auditorium",
            "Kamis",
            "15:00 - 17:00"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dashImageUser.setOnClickListener { view ->
            val popup = PopupMenu(requireContext(), view)
            popup.menuInflater.inflate(R.menu.menu_logout, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_logout -> {
                        showLogoutConfirmation()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        setupNewsRecycler()
        setupAttendanceRecycler()
        setupDashSchedulesRecycler()
    }

    private fun setupDashSchedulesRecycler() {
        dashSchedulesAdapter = DashSchedulesAdapter(allDashCourse)
        binding.dashboardSchedulesUpcoming.apply {

            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = dashSchedulesAdapter

            val spaceDecoration = SpaceItemDecoration(15)
            addItemDecoration(spaceDecoration)
        }
    }

    private fun setupAttendanceRecycler() {
        resumeAdapter = ResumeAdapter(resumePageAll)
        binding.resumeAttendDashRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resumeAdapter
        }
    }

    private fun setupNewsRecycler() {
        newsAdapter = NewsAdapter(newsAll)
        binding.newsNowDashRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun showLogoutConfirmation() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Anda yakin keluar dari akun ini?")

        builder.setPositiveButton("Ya") { _, _ ->
            Toast.makeText(requireContext(), "Logout berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), IntroActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}