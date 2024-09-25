package com.sample.cropview

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.cropview.databinding.ActivityCropBinding

class ActivityCrop : AppCompatActivity() {

    private val binding by lazy { ActivityCropBinding.inflate(layoutInflater) }
    private val uriPath by lazy { intent.getStringExtra("uriPath") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()
        setUI()

        binding.mbBackCrop.setOnClickListener { finish() }
    }

    private fun fullScreen() {
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUI() {
        if (uriPath.isNullOrEmpty()) {
            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show()
            return
        }
        val uri = Uri.parse(uriPath)
        binding.sivImageCrop.setImageURI(uri)
    }
}