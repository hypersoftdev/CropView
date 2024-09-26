package com.sample.cropview.result

import com.sample.cropview.base.BaseActivity
import com.sample.cropview.crop.ActivityCrop
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