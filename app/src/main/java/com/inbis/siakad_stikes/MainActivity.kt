package com.inbis.siakad_stikes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.inbis.siakad_stikes.Adapter.ImageAdapter
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.main.JadwalActivity
import com.inbis.siakad_stikes.main.ProfileActivity
import com.inbis.siakad_stikes.main.RiwayatActivity
import com.inbis.siakad_stikes.main.ScanActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        setupViewPager()
        autoSlideImages()
        actionButton()
    }

    private fun setupViewPager() {
        val imagePairs = listOf(
            Pair(R.drawable.rskm_image, R.drawable.rsud_image),
            Pair(R.drawable.ubhinus, R.drawable.rskm_image),
            Pair(R.drawable.rsud_image, R.drawable.ubhinus)
        )

        imageAdapter = ImageAdapter(imagePairs)
        binding.imageCarousel.adapter = imageAdapter
        binding.imageCarousel.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun autoSlideImages() {
        val update = Runnable {
            if (currentPage == imageAdapter.itemCount) {
                currentPage = 0
            }
            binding.imageCarousel.setCurrentItem(currentPage++, true)
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000) // Slide otomatis setiap 3 detik
    }

    private fun actionButton() {
        binding.btnScan.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.btnSchedule.setOnClickListener {
            startActivity(Intent(this, JadwalActivity::class.java))
        }

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
    }
}
