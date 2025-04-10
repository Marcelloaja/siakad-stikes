package com.inbis.siakad_stikes.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DefaultItemAnimator
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.SpaceItemDecoration
import com.inbis.siakad_stikes.adapter.DashSchedulesAdapter
import com.inbis.siakad_stikes.adapter.NewsAdapter
import com.inbis.siakad_stikes.adapter.ResumeAdapter
import com.inbis.siakad_stikes.auth.IntroActivity
import com.inbis.siakad_stikes.data.NewsData
import com.inbis.siakad_stikes.data.OnGoingData
import com.inbis.siakad_stikes.data.ResumeData
import com.inbis.siakad_stikes.databinding.FragmentDashboardBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var resumeAdapter: ResumeAdapter
    private lateinit var dashSchedulesAdapter: DashSchedulesAdapter
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var refreshHandler: Handler
    private lateinit var refreshRunnable: Runnable
    private val handler = Handler(Looper.getMainLooper())

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
        OnGoingData("Pemrograman Mobile", "Dr. Adi Nugroho", "Ruang Auditorium", "Senin", "08:00 - 10:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Selasa", "06:00 - 08:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Rabu", "07:00 - 08:49"),
        OnGoingData("Kecerdasan Buatan", "Dr. Siti Rahmah", "Ruang Auditorium", "Kamis", "09:00 - 10:00"),
        OnGoingData("Basis Data", "Dr. Rina Sari", "Ruang Auditorium", "Jumat", "10:00 - 12:00"),
        OnGoingData("Jaringan Komputer", "Dr. Budi Santoso", "Ruang Auditorium", "Sabtu", "13:00 - 15:00"),
        OnGoingData("Algoritma dan Struktur Data", "Dr. Ahmad Fauzi", "Ruang 101", "Senin", "11:00 - 13:00"),
        OnGoingData("Sistem Operasi", "Dr. Dian Pratama", "Ruang 102", "Selasa", "09:00 - 11:00"),
        OnGoingData("Pemrograman Web", "Dr. Eka Wijaya", "Ruang 103", "Rabu", "11:00 - 12:00"),
        OnGoingData("Machine Learning", "Dr. Fitriani", "Ruang 104", "Kamis", "12:00 - 14:00"),
        OnGoingData("Keamanan Jaringan", "Dr. Gita Putri", "Ruang 105", "Jumat", "13:00 - 14:00"),
        OnGoingData("Rekayasa Perangkat Lunak", "Dr. Joko Susilo", "Ruang 201", "Senin", "13:30 - 14:30"),
        OnGoingData("Basis Data Lanjut", "Dr. Kartika Dewi", "Ruang 202", "Selasa", "11:30 - 12:30"),
        OnGoingData("Jaringan Nirkabel", "Dr. Lina Hartati", "Ruang 203", "Rabu", "13:30 - 15:30"),
        OnGoingData("Deep Learning", "Dr. Mulyadi", "Ruang 204", "Kamis", "15:30 - 17:30"),
        OnGoingData("Pemrograman Berorientasi Objek", "Dr. Rudi Hartono", "Ruang 301", "Senin", "15:30 - 17:30"),
        OnGoingData("Manajemen Proyek TI", "Dr. Sari Dewi", "Ruang 302", "Selasa", "13:30 - 15:30"),
        OnGoingData("Komputasi Paralel", "Dr. Taufik Hidayat", "Ruang 303", "Rabu", "15:30 - 17:30"),
        OnGoingData("Blockchain", "Dr. Yudi Pratama", "Ruang 307", "Minggu", "15:30 - 17:30")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        indicatorLayout = binding.indicatorLayout

        setupNewsRecycler()
        setupAttendanceRecycler()
        startAutoRefresh()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startAutoRefresh() {
        refreshRunnable = Runnable {
            setupDashSchedulesRecycler()
            handler.postDelayed(refreshRunnable, 60000) // 60 detik refresh
        }
        handler.post(refreshRunnable)
    }

    private fun stopAutoRefresh() {
        handler.removeCallbacks(refreshRunnable)
    }

    private fun setupNewsRecycler() {
        newsAdapter = NewsAdapter(newsAll)
        binding.newsNowDashRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
            itemAnimator = DefaultItemAnimator() // Smooth enter animation
            addItemDecoration(SpaceItemDecoration(20))
        }
    }

    private fun setupAttendanceRecycler() {
        resumeAdapter = ResumeAdapter(resumePageAll)
        binding.resumeAttendDashRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = resumeAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(SpaceItemDecoration(15))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupDashSchedulesRecycler() {
        refreshHandler = Handler(Looper.getMainLooper())
        refreshRunnable = object : Runnable {
            override fun run() {
                val today = LocalDate.now().dayOfWeek
                val todayNameEnglish = today.name.lowercase().replaceFirstChar { it.uppercase() }

                val todayNameIndonesian = when (today) {
                    DayOfWeek.MONDAY -> "Senin"
                    DayOfWeek.TUESDAY -> "Selasa"
                    DayOfWeek.WEDNESDAY -> "Rabu"
                    DayOfWeek.THURSDAY -> "Kamis"
                    DayOfWeek.FRIDAY -> "Jumat"
                    DayOfWeek.SATURDAY -> "Sabtu"
                    DayOfWeek.SUNDAY -> "Minggu"
                }

                val currentTime = LocalTime.now(ZoneId.of("Asia/Jakarta"))

                val todayCourses = allDashCourse.filter {
                    it.courseDay.equals(todayNameEnglish, ignoreCase = true) ||
                            it.courseDay.equals(todayNameIndonesian, ignoreCase = true)
                }

                val sortedTodayCourses = todayCourses.sortedBy { course ->
                    val timeRange = course.courseHour.split("-")
                    if (timeRange.size == 2) {
                        LocalTime.parse(timeRange[0].trim().replace(".", ":"))
                    } else {
                        LocalTime.MAX
                    }
                }

                val ongoingCourses = sortedTodayCourses.filter { course ->
                    val timeRange = course.courseHour.split("-")
                    if (timeRange.size == 2) {
                        val startTime = LocalTime.parse(timeRange[0].trim().replace(".", ":"))
                        val endTime = LocalTime.parse(timeRange[1].trim().replace(".", ":"))

                        val oneHourBefore = currentTime.minusHours(1)
                        (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) ||
                                (oneHourBefore.isAfter(startTime) && currentTime.isBefore(endTime))
                    } else {
                        false
                    }
                }

                val upcomingCourses = sortedTodayCourses.filter { course ->
                    val timeRange = course.courseHour.split("-")
                    if (timeRange.size == 2) {
                        val startTime = LocalTime.parse(timeRange[0].trim().replace(".", ":"))
                        currentTime.isBefore(startTime)
                    } else {
                        false
                    }
                }

                val displayCourses = mutableListOf<OnGoingData>()

                if (ongoingCourses.isNotEmpty()) {
                    displayCourses.addAll(ongoingCourses)
                } else {
                    displayCourses.add(
                        OnGoingData(
                            courseName = "Tidak ada course yang sedang berlangsung",
                            courseLecture = "Hari : $todayNameIndonesian, ${LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("id", "ID")))}",
                            courseRoom = "",
                            courseDay = todayNameIndonesian,
                            courseHour = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                        )
                    )
                }

                displayCourses.addAll(upcomingCourses)

                // Kalau adapter sudah ada, update datanya aja, jangan buat baru
                if (::dashSchedulesAdapter.isInitialized) {
                    dashSchedulesAdapter.updateData(displayCourses)
                } else {
                    dashSchedulesAdapter = DashSchedulesAdapter(displayCourses)
                    binding.dashboardSchedulesUpcoming.apply {
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                        adapter = dashSchedulesAdapter
                        itemAnimator = DefaultItemAnimator()
                        val snapHelper = PagerSnapHelper()
                        snapHelper.attachToRecyclerView(this)
                        addItemDecoration(SpaceItemDecoration(30))
                        setupIndicators(dashSchedulesAdapter.itemCount)
                        setCurrentIndicator(0)

                        addOnScrollListener(object : RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                                val position = layoutManager.findFirstVisibleItemPosition()
                                setCurrentIndicator(position)
                            }
                        })
                    }
                }


                // Set refresh ulang setiap 1 menit
                refreshHandler.postDelayed(this, 1000)
            }
        }

        // Jalankan pertama kali
        refreshHandler.post(refreshRunnable)
    }

    override fun onDestroyView() {
        stopAutoRefresh()
        super.onDestroyView()
        if (::refreshHandler.isInitialized) {
            refreshHandler.removeCallbacks(refreshRunnable)
        }
    }

    private fun setupIndicators(count: Int) {
        indicatorLayout.removeAllViews()

        val indicators = Array(count) { ImageView(requireContext()) }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)

        indicators.forEach { imageView ->
            imageView.layoutParams = params
            imageView.setImageResource(R.drawable.indicator_inactive_line)  // pakai shape garis
            indicatorLayout.addView(imageView)
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorLayout.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorLayout.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageResource(R.drawable.indicator_active_line)
            } else {
                imageView.setImageResource(R.drawable.indicator_inactive_line)
            }
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                startActivity(Intent(requireContext(), IntroActivity::class.java))
                requireActivity().finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

}