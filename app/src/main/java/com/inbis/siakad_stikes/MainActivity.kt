package com.inbis.siakad_stikes

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.inbis.siakad_stikes.Adapter.ImageAdapter
import androidx.core.animation.doOnEnd
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.main.JadwalActivity
import com.inbis.siakad_stikes.main.ProfileActivity
import com.inbis.siakad_stikes.main.RiwayatActivity
import com.inbis.siakad_stikes.main.ScanActivity
import java.util.*
import com.inbis.siakad_stikes.sidefeatures.NotificationActivity

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
        enableEdgeToEdge()
        actionButton()
    }

    private fun setupViewPager() {
        val imagePairs = listOf(
            Pair(R.drawable.rskm_image, R.drawable.rsud_image),
            Pair(R.drawable.rsud_image, R.drawable.ubhinus),
            Pair(R.drawable.ubhinus, R.drawable.rskm_image)
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
            val btnScan = binding.btnScan
            val targetIntent = ScanActivity::class.java
            animateButton(btnScan, this, targetIntent)
        }

        binding.btnProfile.setOnClickListener {
            val btnProf = binding.btnProfile
            val targetIntent = ProfileActivity::class.java
            animateButton(btnProf, this, targetIntent)
        }

        binding.btnSchedule.setOnClickListener {
            val btnSchedule = binding.btnSchedule
            val targetIntent = JadwalActivity::class.java
            animateButton(btnSchedule, this, targetIntent)
        }

        binding.btnHistory.setOnClickListener {
            val btnHistory = binding.btnHistory
            val targetIntent = RiwayatActivity::class.java
            animateButton(btnHistory, this, targetIntent)
        }

        binding.btnNotification.setOnClickListener {
            val btnNotif = binding.btnNotification
            val targetIntent = NotificationActivity::class.java
            animateButton(btnNotif, this, targetIntent)
        }
    }

    private fun animateButton(
        button: ImageButton,
        context: Context,
        targetActivity: Class<out Activity>,
    ) {
        val button = button

        val scaleDownX = ObjectAnimator.ofFloat(button, "scaleX", 0.9f)
        val scaleDownY = ObjectAnimator.ofFloat(button, "scaleY", 0.9f)
        val scaleUpX = ObjectAnimator.ofFloat(button, "scaleX", 1f)
        val scaleUpY = ObjectAnimator.ofFloat(button, "scaleY", 1f)

        val animatorSet = AnimatorSet()
        animatorSet.play(scaleDownX).with(scaleDownY)
        animatorSet.play(scaleUpX).with(scaleUpY).after(scaleDownX)

        animatorSet.duration = 150
        animatorSet.start()

        animatorSet.doOnEnd {
            val intent = Intent(this, targetActivity)
            context.startActivity(intent)
        }

    }
}
