package com.sample.cropview.ui.result

import com.sample.cropview.ui.base.BaseActivity
import com.sample.cropview.ui.crop.ActivityCrop
import com.sample.cropview.databinding.ActivityResultBinding

class ActivityResult : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {

    override fun onCreated() {
        setUI()

        binding.mbBackCrop.setOnClickListener { finish() }
    }

    private fun setUI() {
        binding.sivImageResult.setImageBitmap(ActivityCrop.bitmap)
    }
}