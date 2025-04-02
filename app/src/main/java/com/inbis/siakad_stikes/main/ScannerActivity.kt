package com.inbis.siakad_stikes.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.inbis.siakad_stikes.R
import com.inbis.siakad_stikes.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannerBinding
    private lateinit var codeScan: CodeScanner
    private var toast: Toast? = null
    private var initialDistance = 0f
    private var currentZoomLevel = 1f
    private lateinit var zoomLevelText: TextView
    private var isToastVisible = false // Tambahkan variabel global untuk melacak status toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        zoomLevelText = findViewById(R.id.zoomLevelText)

        codeScanner()
        setPermission()

        binding.scannerBarcode.setOnTouchListener { v, event ->
            handleZoomGesture(event)
            false
        }
    }

    private fun codeScanner() {
        codeScan = CodeScanner(this, binding.scannerBarcode)
        codeScan.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    showCustomToast(true)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    showCustomToast(false)
                }
            }

            binding.scannerBarcode.setOnClickListener {
                codeScan.startPreview()
            }
        }
    }

    private fun showCustomToast(isSuccess: Boolean) {
        if (isToastVisible) return // Jika toast masih tampil, jangan tampilkan toast baru
        isToastVisible = true // Tandai bahwa toast sedang ditampilkan

        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        val toastIcon = layout.findViewById<ImageView>(R.id.toast_icon)
        val toastTitle = layout.findViewById<TextView>(R.id.toast_title)
        val toastMessage = layout.findViewById<TextView>(R.id.toast_message)
        val toastClose = layout.findViewById<ImageView>(R.id.toast_close)
        val toastProgress = layout.findViewById<ProgressBar>(R.id.toast_progress)

        if (isSuccess) {
            toastIcon.setImageResource(R.drawable.ic_success)
            toastTitle.text = "Absen Berhasil!"
            toastMessage.text = "Silahkan check ulang nama Anda pada layar proyektor."
        } else {
            toastIcon.setImageResource(R.drawable.ic_warning)
            toastTitle.text = "Absen Gagal!"
            toastMessage.text = "Silakan coba lagi."
        }

        toast?.cancel()

        toast = Toast(applicationContext).apply {
            duration = Toast.LENGTH_LONG
            view = layout
            setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 100)
        }

        toastProgress.max = 100
        val handler = Handler(Looper.getMainLooper())

        var progress = 100
        val runnable = object : Runnable {
            override fun run() {
                if (progress >= 0) {
                    toastProgress.progress = progress
                    progress -= 2
                    handler.postDelayed(this, 60)
                } else {
                    handler.postDelayed({
                        toast?.cancel()
                        isToastVisible = false // Reset status toast setelah selesai
                    }, 10000)
                }
            }
        }
        handler.post(runnable)

        toastClose.setOnClickListener {
            toast?.cancel()
            isToastVisible = false // Reset status toast jika ditutup manual
        }
        toast?.show()
    }

    private fun handleZoomGesture(event: MotionEvent) {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                initialDistance = getDistance(event)
            }

            MotionEvent.ACTION_MOVE -> {
                if (event.pointerCount == 2) {
                    val newDistance = getDistance(event)
                    val scale = newDistance / initialDistance
                    val newZoomLevel = currentZoomLevel * scale
                    if (newZoomLevel in 1f..15f) {
                        codeScan.setZoom(newZoomLevel.toInt())
                        currentZoomLevel = newZoomLevel

                        // Update Zoom Level TextView
                        zoomLevelText.text = "Zoom: ${"%.1f".format(currentZoomLevel)}x"
                    }
                }
            }

            MotionEvent.ACTION_POINTER_UP -> {
                initialDistance = 0f
            }
        }
    }

    private fun getDistance(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }

    override fun onResume() {
        super.onResume()
        codeScan.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScan.releaseResources()
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        }
    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), 101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Dibutuhkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
