package com.hypersoft.cropview.ui.result

import com.hypersoft.cropview.ui.base.BaseActivity
import com.hypersoft.cropview.ui.crop.ActivityCrop
import com.hypersoft.cropview.databinding.ActivityResultBinding

class ActivityResult : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {

    override fun onCreated() {
        setUI()

        binding.mbBackCrop.setOnClickListener { finish() }
    }

    private fun setUI() {
        binding.sivImageResult.setImageBitmap(ActivityCrop.bitmap)
    }
}