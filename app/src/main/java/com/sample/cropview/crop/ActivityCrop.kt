package com.sample.cropview.crop

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.RectF
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.sample.cropview.ViewModelCrop
import com.sample.cropview.base.BaseActivity
import com.sample.cropview.databinding.ActivityCropBinding
import com.sample.cropview.result.ActivityResult

class ActivityCrop : BaseActivity<ActivityCropBinding>(ActivityCropBinding::inflate) {

    private val viewModel by viewModels<ViewModelCrop>()

    private val uriPath by lazy { intent.getStringExtra("uriPath") }

    override fun onCreated() {
        setUI()
        initCropView()
        initObservers()

        binding.mbBackCrop.setOnClickListener { finish() }
        binding.mbSaveCrop.setOnClickListener { saveImage() }
    }

    private fun setUI() {
        if (uriPath.isNullOrEmpty()) {
            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show()
            return
        }
        val uri = Uri.parse(uriPath)
        viewModel.getBitmap(uri)
    }

    private fun initCropView() = with(binding.cropView) {
        onInitialized = { updatePixels(getCropSizeOriginal()) }
        observeCropRectOnOriginalBitmapChanged = { updatePixels(it) }
    }

    private fun updatePixels(rectF: RectF) {
        val cropWidth = "W: ${rectF.width().toInt()}"
        val cropHeight = "H: ${rectF.height().toInt()}"
        binding.mtvWidthCrop.text = cropWidth
        binding.mtvHeightCrop.text = cropHeight
    }

    private fun initObservers() {
        viewModel.bitmapLiveData.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.cropView.setBitmap(it)
        }
        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImage() {
        bitmap = binding.cropView.getCroppedData().croppedBitmap
        startActivity(Intent(this, ActivityResult::class.java))
    }

    companion object {
        var bitmap: Bitmap? = null
    }

}