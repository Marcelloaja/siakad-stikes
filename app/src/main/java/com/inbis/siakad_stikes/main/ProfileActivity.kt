package com.inbis.siakad_stikes.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.inbis.siakad_stikes.MainActivity
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityProfileBinding
import com.inbis.siakad_stikes.fragment.DataAcademicFragment
import com.inbis.siakad_stikes.fragment.DataPrivateFragment

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        showInformation()
        goToData(DataPrivateFragment())
        actionButton()
    }

    private fun actionButton() {
        binding.btnBackProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showInformation() {
        binding.btnDataPrivate.setOnClickListener {
            goToData(DataPrivateFragment())
            binding.btnDataPrivate.setImageResource(R.drawable.btn_pribadi_w)
            binding.btnDataAcademic.setImageResource(R.drawable.btn_akademik_b)
        }
        binding.btnDataAcademic.setOnClickListener {
            goToData(DataAcademicFragment())
            binding.btnDataPrivate.setImageResource(R.drawable.btn_pribadi_b)
            binding.btnDataAcademic.setImageResource(R.drawable.btn_akademik_w)
        }
    }

    private fun goToData(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}