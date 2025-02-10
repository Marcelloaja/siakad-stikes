package com.inbis.siakad_stikes.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.inbis.siakad_stikes.databinding.ActivityScanBinding
import java.util.concurrent.Executors

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private var camera: Camera? = null
    private lateinit var cameraControl: CameraControl
    private val cameraExecutor = Executors.newSingleThreadExecutor()
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startCamera()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        setupPinchToZoom()
    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                cameraControl = camera!!.cameraControl
            } catch (exc: Exception) {
                // Handle camera binding error
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun setupPinchToZoom() {
        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val zoomRatio = camera?.cameraInfo?.zoomState?.value?.zoomRatio ?: 1.0f
                val newZoomRatio = zoomRatio * detector.scaleFactor
                cameraControl.setZoomRatio(newZoomRatio.coerceIn(1.0f, 10.0f))
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return scaleGestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
