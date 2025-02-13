package com.inbis.siakad_stikes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.FragmentOnGoingSchedulesBinding


class OnGoingSchedulesFragment : Fragment() {

    private var _binding: FragmentOnGoingSchedulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnGoingSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }


}