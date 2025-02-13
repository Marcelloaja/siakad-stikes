package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.FragmentCompletedSchedulesBinding


class CompletedSchedulesFragment : Fragment() {

    private var _binding: FragmentCompletedSchedulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateSchedulesAction(binding.btnCourseAll)

        binding.btnCourseAll.setOnClickListener { updateSchedulesAction(binding.btnCourseAll) }
        binding.btnCourseMandatory.setOnClickListener { updateSchedulesAction(binding.btnCourseMandatory) }
        binding.btnCourseElective.setOnClickListener { updateSchedulesAction(binding.btnCourseElective) }
    }

    private fun updateSchedulesAction(selectedButton: MaterialButton) {
        val button = listOf(binding.btnCourseAll, binding.btnCourseMandatory, binding.btnCourseElective)

        button.forEach { button ->
            if (button == selectedButton) {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.new_orange))
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                button.strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.new_grey2)
            } else {
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                button.strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.new_grey2)
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}