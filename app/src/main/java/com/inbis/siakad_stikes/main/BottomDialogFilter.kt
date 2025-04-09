package com.inbis.siakad_stikes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.inbis.siakad_stikes.R

class BottomDialogFilter : BottomSheetDialogFragment() {

    private var semesterList: Array<String>? = null

    companion object {
        private const val ARG_SEMESTER_LIST = "SEMESTER_LIST"

        fun newInstance(semesterList: Array<String>): BottomDialogFilter {
            val fragment = BottomDialogFilter()
            val args = Bundle().apply {
                putStringArray(ARG_SEMESTER_LIST, semesterList)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            semesterList = it.getStringArray(ARG_SEMESTER_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_dialog_semester, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val semesterContainer = view.findViewById<LinearLayout>(R.id.semesterListContainer)

        semesterList?.forEach { semester ->
            val textView = TextView(requireContext()).apply {
                text = semester
                textSize = 18f
                setPadding(16, 16, 16, 16)
                setOnClickListener {
                    // TODO: Handle saat item semester diklik
                    println("Semester dipilih: $semester")
                    dismiss() // Tutup bottom sheet kalau mau
                }
            }
            semesterContainer.addView(textView)
        }
    }
}
