package com.inbis.siakad_stikes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inbis.siakad_stikes.adapter.HourButtonAdapter
import com.inbis.siakad_stikes.adapter.OnGoingCourseAdapter
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.data.HourItemData
import com.inbis.siakad_stikes.data.OnGoingData
import com.inbis.siakad_stikes.fragment.DashboardFragment
import com.inbis.siakad_stikes.fragment.OnGoingSchedulesFragment
import com.inbis.siakad_stikes.fragment.ProfileFragment
import com.inbis.siakad_stikes.fragment.RiwayatFragment
import com.inbis.siakad_stikes.main.ScannerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0
    private var currentFragmentTag: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser == null) {
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
//            return  // Tambahkan return agar kode setelahnya tidak dieksekusi
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
//        supportActionBar?.hide()

        binding.navView.background = null
        val navigateTo = intent.getStringExtra("navigate_to")
        if (navigateTo != null) {
            when (navigateTo) {
                "HomeFragment" -> replaceFragment(DashboardFragment(), "HomeFragment", false)
                "ScheduleFragment" -> replaceFragment(OnGoingSchedulesFragment(), "ScheduleFragment", false)
                "ScanFragment" -> {
                    val intent = Intent(this, ScannerActivity::class.java)
                    startActivity(intent)
                }
                "RiwayatFragment" -> replaceFragment(RiwayatFragment(), "RiwayatFragment", false)
                "ProfileFragment" -> replaceFragment(ProfileFragment(), "ProfileFragment", false)
            }
        } else {
            replaceFragment(DashboardFragment(), "HomeFragment", false)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
            binding.navView.menu.setGroupCheckable(0, true, false)
            for (i in 0 until binding.navView.menu.size()) {
                binding.navView.menu.getItem(i).isChecked = false
            }
            binding.navView.menu.setGroupCheckable(0, true, true)
        }
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(DashboardFragment(), "HomeFragment", true)
                R.id.navigation_schedule -> replaceFragment(OnGoingSchedulesFragment(), "MapsFragment", true)
                R.id.navigation_riwayat -> replaceFragment(RiwayatFragment(), "RiwayatFragment", true)
                R.id.navigation_profile -> replaceFragment(ProfileFragment(), "ProfileFragment", true)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        if (currentFragmentTag == tag) return

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.replace(R.id.navhost, fragment, tag)
            fragmentTransaction.addToBackStack(tag)
        } else {
            fragmentTransaction.replace(R.id.navhost, fragment, tag)
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        fragmentTransaction.commit()
        currentFragmentTag = tag
    }

//    override fun onBackPressed() {
//        val currentTime = System.currentTimeMillis()
//        if (currentTime - backPressedTime < 2000) {
//            super.onBackPressed()
//        } else {
//            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
//            backPressedTime = currentTime
//        }
//    }
}
