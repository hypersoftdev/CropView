package com.sample.cropview

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.sample.cropview.ui.base.BaseActivity
import com.sample.cropview.ui.crop.ActivityCrop
import com.sample.cropview.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        validateUri(uri)
    }

    override fun onCreated() {
        binding.mbChooseImage.setOnClickListener { getContent.launch("image/*") }
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