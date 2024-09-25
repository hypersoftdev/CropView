package com.sample.cropview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.cropview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        validateUri(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreen()

        binding.mbChooseImage.setOnClickListener { getContent.launch("image/*") }
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

    private fun validateUri(uri: Uri?) {
        when (uri == null) {
            true -> Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show()
            false -> navigateScreen(uri)
        }
    }

    private fun navigateScreen(uri: Uri) {
        val uriPath = uri.toString()
        val intent = Intent(this, ActivityCrop::class.java).apply {
            putExtra("uriPath", uriPath)
        }
        startActivity(intent)
    }
}