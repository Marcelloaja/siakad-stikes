package com.inbis.siakad_stikes.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.inbis.siakad_stikes.MainActivity
import com.inbis.siakad_stikes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Data dummy untuk email dan password
        binding.etEmail.setText("admin@gmail.com")
        binding.etPassword.setText("admin")

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi email
            if (isValidEmail(email)) {
                // Cek jika password juga sesuai
                if (password == "admin") {
                    animateButtonAndNavigate()
                } else {
                    Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            }
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Fungsi untuk memvalidasi email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
