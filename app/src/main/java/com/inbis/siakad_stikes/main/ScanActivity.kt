package com.inbis.siakad_stikes.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.inbis.siakad_stikes.MainActivity
import com.inbis.siakad_stikes.databinding.ActivityScanBinding
import java.util.concurrent.Executors

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private val cameraExecutor = Executors.newSingleThreadExecutor()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startQRScanner()
        } else {
            Toast.makeText(this, "Izin kamera diperlukan untuk scan QR", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.successLottie.visibility = View.GONE
        binding.unsuccessfulLottie.visibility = View.GONE
        binding.absenText.visibility = View.GONE
        binding.retryButton.visibility = View.GONE

        if (allPermissionsGranted()) {
            startQRScanner()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a QR code")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.setCaptureActivity(CaptureActivityPortrait::class.java)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents != null && result.formatName == "QR_CODE") {
                // Jika scan berhasil
                binding.unsuccessfulLottie.visibility = View.GONE
                binding.retryButton.visibility = View.GONE

                binding.successLottie.visibility = View.VISIBLE
                binding.successLottie.playAnimation()

                binding.absenText.visibility = View.VISIBLE
                binding.absenText.text = "Absen Success"

                Toast.makeText(this, "Scan berhasil: ${result.contents}", Toast.LENGTH_LONG).show()

                // Delay 3 detik lalu pindah ke MainActivity
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            } else {
                binding.successLottie.visibility = View.GONE
                binding.absenText.visibility = View.GONE

                binding.unsuccessfulLottie.visibility = View.VISIBLE
                binding.unsuccessfulLottie.playAnimation()

                binding.retryButton.visibility = View.VISIBLE

                Toast.makeText(this, "Scan gagal atau bukan QR Code", Toast.LENGTH_LONG).show()
            }
        } else {
            binding.successLottie.visibility = View.GONE
            binding.absenText.visibility = View.GONE

            binding.unsuccessfulLottie.visibility = View.VISIBLE
            binding.unsuccessfulLottie.playAnimation()

            binding.retryButton.visibility = View.VISIBLE

            Toast.makeText(this, "Scan dibatalkan atau jaringan tidak tersedia", Toast.LENGTH_LONG).show()
        }
    }

    fun retryScan(view: View) {
        binding.unsuccessfulLottie.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        startQRScanner()
    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
