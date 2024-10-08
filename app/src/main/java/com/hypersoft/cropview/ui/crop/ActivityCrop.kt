package com.hypersoft.cropview.ui.crop

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.RectF
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.hypersoft.crop.enums.AspectRatioType
import com.hypersoft.crop.util.extensions.toDp
import com.hypersoft.crop.util.extensions.toPx
import com.hypersoft.cropview.R
import com.hypersoft.cropview.databinding.ActivityCropBinding
import com.hypersoft.cropview.helpers.adapter.AdapterAspectRatio
import com.hypersoft.cropview.ui.base.BaseActivity
import com.hypersoft.cropview.ui.result.ActivityResult

class ActivityCrop : BaseActivity<ActivityCropBinding>(ActivityCropBinding::inflate) {

    private val adapterAspectRatio by lazy { AdapterAspectRatio(itemClick) }
    private val viewModel by viewModels<ViewModelCrop>()

    private val uriPath by lazy { intent.getStringExtra("uriPath") }

    override fun onCreated() {
        setUI()
        initCropView()
        initObservers()
        initRecyclerView()
        setAdapters()

        binding.mbBackCrop.setOnClickListener { finish() }
        binding.mbSaveCrop.setOnClickListener { saveImage() }
        binding.mbRotateLeftCrop.setOnClickListener { binding.cropView.setCropRotation(-90f) }
        binding.mbRotateRightCrop.setOnClickListener { binding.cropView.setCropRotation(90f) }
        binding.sliderCornerLength.addOnChangeListener { _, value, _ -> onCornerLengthSliderChange(value) }
        binding.sliderCornerWidth.addOnChangeListener { _, value, _ -> onCornerWidthSliderChange(value) }
        binding.sliderGridLineWidth.addOnChangeListener { _, value, _ -> onGridLineWidthSliderChange(value) }
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
        onInitialized = {
            updateSliders()
            updatePixels(getCropSizeOriginal())
        }
        observeCropRectOnOriginalBitmapChanged = { updatePixels(it) }
    }

    private fun updateSliders() {
        val cornerLength = binding.cropView.getCropCornerLength().toDp(resources)
        binding.sliderCornerLength.value = cornerLength
        binding.mtvCornerLength.text = getString(R.string.corner_length, cornerLength.toInt())

        val cornerWidth = binding.cropView.getCropCornerWidth().toDp(resources)
        binding.sliderCornerWidth.value = cornerWidth
        binding.mtvCornerWidth.text = getString(R.string.corner_width, cornerWidth.toInt())

        val gridLineWidth = binding.cropView.getGridlineWidth().toDp(resources)
        binding.sliderGridLineWidth.value = gridLineWidth
        binding.mtvGridLineWidth.text = getString(R.string.grid_line_width, gridLineWidth.toInt())
    }

    private fun updatePixels(rectF: RectF) {
        val cropWidth = "W: ${rectF.width().toInt()}"
        val cropHeight = "H: ${rectF.height().toInt()}"
        binding.mtvWidthCrop.text = cropWidth
        binding.mtvHeightCrop.text = cropHeight
    }

    private fun initObservers() {
        viewModel.aspectRatioLiveData.observe(this) {
            adapterAspectRatio.submitList(it)
        }
        viewModel.bitmapLiveData.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.cropView.setBitmap(it)
        }
        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        binding.rcvListCrop.adapter = adapterAspectRatio
    }

    private fun setAdapters() {
        val dragStateNames = arrayOf("All", "Corners only", "Edge only", "Image only", "Corners & Edge only")
        val colorNames = arrayOf("Red", "Green", "Blue", "Black", "White")
        val colorValues = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.WHITE)

        val adapterCornerColor = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorNames)
        adapterCornerColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCornerColor.adapter = adapterCornerColor
        binding.spinnerCornerColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedColor = colorValues[position]
                binding.cropView.setCropCornerColor(selectedColor)
            }
        }
        binding.spinnerCornerColor.setSelection(2)

        val adapterGridlineColor = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorNames)
        adapterGridlineColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGridlineColor.adapter = adapterGridlineColor
        binding.spinnerGridlineColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedColor = colorValues[position]
                binding.cropView.setGridlineColor(selectedColor)
            }
        }
        binding.spinnerGridlineColor.setSelection(4)

        val adapterDragStateColor = ArrayAdapter(this, android.R.layout.simple_spinner_item, dragStateNames)
        adapterDragStateColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDragStateColor.adapter = adapterDragStateColor
        binding.spinnerDragStateColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.cropView.setDragState(position)
            }
        }
    }

    private fun saveImage() {
        bitmap = binding.cropView.getCroppedData()
        startActivity(Intent(this, ActivityResult::class.java))
    }

    private fun onCornerLengthSliderChange(value: Float) {
        binding.cropView.setCropCornerLength(value.toPx(resources))
        binding.mtvCornerLength.text = getString(R.string.corner_length, value.toInt())
    }

    private fun onCornerWidthSliderChange(value: Float) {
        binding.cropView.setCropCornerWidth(value.toPx(resources))
        binding.mtvCornerWidth.text = getString(R.string.corner_width, value.toInt())
    }

    private fun onGridLineWidthSliderChange(value: Float) {
        binding.cropView.setGridlineWidth(value.toPx(resources))
        binding.mtvGridLineWidth.text = getString(R.string.grid_line_width, value.toInt())
    }

    private val itemClick: (AspectRatioType) -> Unit = {
        binding.cropView.setAspectRatio(it)
        viewModel.itemRatioSelection(it)
    }

    companion object {
        var bitmap: Bitmap? = null
    }
}