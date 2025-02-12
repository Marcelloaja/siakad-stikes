package com.inbis.siakad_stikes

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.inbis.siakad_stikes.databinding.ActivityMainBinding
import com.inbis.siakad_stikes.main.JadwalActivity
import com.inbis.siakad_stikes.main.ProfileActivity
import com.inbis.siakad_stikes.main.RiwayatActivity
import com.inbis.siakad_stikes.main.ScanActivity
import com.inbis.siakad_stikes.sidefeatures.NotificationActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        actionButton()

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

    private fun animateButton(button : ImageButton, context: Context, targetActivity: Class<out Activity>) {
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