package com.inbis.siakad_stikes.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.hide()

        binding.btnStart.setOnClickListener {
            animateButtonAndNavigate()
        }
    }

    private fun animateButtonAndNavigate() {
        val button = binding.btnStart

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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}