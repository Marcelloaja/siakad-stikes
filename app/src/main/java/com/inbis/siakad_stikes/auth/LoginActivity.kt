package com.inbis.siakad_stikes.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.inbis.siakad_stikes.databinding.ActivityLoginBinding
import com.inbis.siakad_stikes.main.ScanActivity
import com.inbis.siakad_stikes.main.ScannerActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            animateButtonAndNavigate()
        }
    }

    private fun animateButtonAndNavigate() {
        val button = binding.btnLogin

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
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
    }

}
