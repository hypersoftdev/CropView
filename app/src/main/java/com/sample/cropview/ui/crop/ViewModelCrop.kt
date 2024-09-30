package com.sample.cropview.ui.crop

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.cropview.helpers.dp.DpAspectRatio
import com.sample.cropview.helpers.models.AspectRatio
import dev.pegasus.crop.enums.AspectRatioType
import dev.pegasus.crop.util.bitmaps.BitmapUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/26/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

class ViewModelCrop(private val application: Application) : AndroidViewModel(application) {

    private val _aspectRatioLiveData = MutableLiveData<List<AspectRatio>>()
    val aspectRatioLiveData: LiveData<List<AspectRatio>> get() = _aspectRatioLiveData

    private val _bitmapLiveData = MutableLiveData<Bitmap>()
    val bitmapLiveData: LiveData<Bitmap> get() = _bitmapLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    init {
        fetchAspectRatioList()
    }

    private fun fetchAspectRatioList() = viewModelScope.launch(Dispatchers.IO) {
        val list = DpAspectRatio().list
        list[0].isSelected = true
        _aspectRatioLiveData.postValue(list)
    }

    fun getBitmap(uri: Uri) = viewModelScope.launch(Dispatchers.IO) {
        BitmapUtils.resize(uri, application)?.let {
            _bitmapLiveData.postValue(it)
        } ?: run {
            _errorLiveData.postValue("Failed to process image, try again later")
        }
    }

    fun itemRatioSelection(aspectRatioType: AspectRatioType) {
        val list = DpAspectRatio().list
        list.find { it.aspectRatioType == aspectRatioType }?.isSelected = true
        _aspectRatioLiveData.postValue(list)
    }
}