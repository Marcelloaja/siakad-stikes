package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.inbis.siakad_stikes.adapter.ResumeAdapter
import com.inbis.siakad_stikes.data.ResumeData
import com.inbis.siakad_stikes.databinding.FragmentProfileBinding
import com.inbis.siakad_stikes.main.BottomDialogFilter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var resumeAdapter: ResumeAdapter
    private val handler = Handler(Looper.getMainLooper())

    private val semesterList = arrayOf(
        "Semester 1", "Semester 2", "Semester 3", "Semester 4",
        "Semester 5", "Semester 6", "Semester 7", "Semester 8"
    )

    private val allResume = listOf(
        ResumeData("Pengadaan Tanggulangan Kesehatan Masyarakat", "Dr. Indra Wijaya, M.Ps", 10, 3, 2, 10),
        ResumeData("Intensifitas Kadar Gula Pada Harimau Malaya", "Suparto, S.Pt, M.U.I", 8, 1, 1, 8),
        ResumeData("Penyaringan Infus Janin Dengan Selang", "Dr. Tirta M.Pd", 12, 1, 2, 12),
        ResumeData("Rekayasa Agama", "Dr. Ustadz Haji Salim", 11, 2, 2, 11),
        ResumeData("Pergantian Zaman dengan Metode Hijrah", "Dr. Freeze", 14, 1, 1, 14),
        ResumeData("Teknologi Peradaban Ghoib", "Marcello Ilham", 16, 0, 0, 16),
        ResumeData("Pencairan Es Batu : Teknik Adaptasi", "Kim Sun Chul, S.Pd, Alm", 16, 0, 0, 16),
        ResumeData("Sistem Pengembangan Bisnis Gelap", "Dr. Sukijat, M.MT", 16, 1, 1, 16)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupResumeRecyclerView()
        setupSettingButton()
    }

    private fun setupResumeRecyclerView() {
        resumeAdapter = ResumeAdapter(allResume)
        binding.resumeAttendanceUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resumeAdapter
        }
    }

    private fun setupSettingButton() {
        binding.btnSetting.setOnClickListener {
            showBottomDialogFilter()
        }
    }

    private fun showBottomDialogFilter() {
        val bottomDialog = BottomDialogFilter.newInstance(semesterList)
        bottomDialog.show(parentFragmentManager, BottomDialogFilter::class.java.simpleName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }
}
