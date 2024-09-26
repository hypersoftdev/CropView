package com.sample.cropview

import android.graphics.RectF
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.sample.cropview.base.BaseActivity
import com.sample.cropview.databinding.ActivityCropBinding

class ActivityCrop : BaseActivity<ActivityCropBinding>(ActivityCropBinding::inflate) {

    private val viewModel by viewModels<ViewModelCrop>()

    private val uriPath by lazy { intent.getStringExtra("uriPath") }

    override fun onCreated() {
        setUI()
        initCropView()
        initObservers()

        binding.mbBackCrop.setOnClickListener { finish() }
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
}