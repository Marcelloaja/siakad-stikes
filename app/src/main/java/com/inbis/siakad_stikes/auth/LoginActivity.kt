package com.inbis.siakad_stikes.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd =
                    binding.loginPassword.compoundDrawablesRelative[2] // Ambil icon di drawableEnd
                if (drawableEnd != null) {
                    val bounds = drawableEnd.bounds
                    val clickAreaStart = binding.loginPassword.right - bounds.width() - 20
                    val clickAreaEnd = binding.loginPassword.right

                    if (event.rawX >= clickAreaStart && event.rawX <= clickAreaEnd) {
                        togglePasswordVisibility()
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    private fun togglePasswordVisibility() {
        if(isPasswordVisible) {
            binding.loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0)
        } else {
            binding.loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0)
        }

        binding.loginPassword.setSelection(binding.loginPassword.text.length)

        isPasswordVisible = !isPasswordVisible
    }


}
